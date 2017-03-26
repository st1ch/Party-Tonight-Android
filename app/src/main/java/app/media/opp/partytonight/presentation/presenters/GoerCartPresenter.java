package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.paypal.android.MEP.PayPalAdvancedPayment;
import com.paypal.android.MEP.PayPalReceiverDetails;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.Booking;
import app.media.opp.partytonight.domain.Bottle;
import app.media.opp.partytonight.domain.CartItemExtended;
import app.media.opp.partytonight.domain.Table;
import app.media.opp.partytonight.domain.Transaction;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.GoerCartConfirmUseCase;
import app.media.opp.partytonight.domain.usecase.GoerCartUseCase;
import app.media.opp.partytonight.domain.usecase.GoerCartValidateUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IGoerCartView;
import okhttp3.ResponseBody;

@UserScope
public class GoerCartPresenter extends ProgressPresenter<IGoerCartView> implements IGoerCartPresenter {
    private List<Transaction> lastTransactions = new ArrayList<>();
    private List<Booking> lastBookings = new ArrayList<>();

    private GoerCartUseCase useCase;
    private GoerCartConfirmUseCase confirmationUseCase;
    private GoerCartValidateUseCase validationUseCase;

    @Inject
    public GoerCartPresenter(Messages messages, GoerCartUseCase useCase,
                             GoerCartConfirmUseCase confirmationUseCase,
                             GoerCartValidateUseCase validationUseCase) {
        super(messages);
        this.useCase = useCase;
        this.confirmationUseCase = confirmationUseCase;
        this.validationUseCase = validationUseCase;
    }

    @Override
    public void onCreate(IGoerCartView view) {
        super.onCreate(view);
        if (useCase.isWorking()) {
            useCase.execute(getTransactionsSubscriber());
        }
        if (confirmationUseCase.isWorking()) {
            confirmationUseCase.execute(getConfirmationSubscriber());
        }
        if (validationUseCase.isWorking()) {
            validationUseCase.execute(getValidationSubscriber());
        }
    }

    @Override
    public void validateOrder(List<Booking> order) {
        validationUseCase.setBookings(order);
        validationUseCase.execute(getValidationSubscriber());
    }

    private void validateOrderForPaying(List<Booking> order) {
        validationUseCase.setBookings(order);
        validationUseCase.execute(getValidationForPayingSubscriber());
    }

    private void onOrderSent(List<Booking> order, boolean validated) {
        if (validated) {
            validateOrderForPaying(order);
            return;
        }

        lastBookings = order;

        useCase.setOrder(order);
        useCase.execute(getTransactionsSubscriber());
    }

    @Override
    public void onOrderSent(List<Booking> order) {
        onOrderSent(order, false);
    }

    @Override
    public void onRelease() {
        useCase.unsubscribe();
        confirmationUseCase.unsubscribe();
        super.onRelease();
    }

    @NonNull
    private BaseProgressSubscriber<List<Transaction>> getTransactionsSubscriber() {
        return new BaseProgressSubscriber<List<Transaction>>(this) {

            @Override
            public void onNext(List<Transaction> response) {
                super.onNext(response);
                IGoerCartView view = getView();

                compilePaymentsForPayPal(response);
            }
        };
    }

    @NonNull
    private BaseProgressSubscriber<ResponseBody> getConfirmationSubscriber() {
        return new BaseProgressSubscriber<ResponseBody>(this) {

            @Override
            public void onNext(ResponseBody response) {
                super.onNext(response);
                IGoerCartView view = getView();

                // TODO: 3/25/17 handle ok or not
                try {
                    Log.i("CONFIRMATION", response.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @NonNull
    private BaseProgressSubscriber<List<Booking>> getValidationSubscriber() {
        return new BaseProgressSubscriber<List<Booking>>(this) {

            @Override
            public void onNext(List<Booking> response) {
                super.onNext(response);
                IGoerCartView view = getView();

                view.handleValidatedCart(handleValidatedOrder(response));
            }
        };
    }

    @NonNull
    private BaseProgressSubscriber<List<Booking>> getValidationForPayingSubscriber() {
        return new BaseProgressSubscriber<List<Booking>>(this) {

            @Override
            public void onNext(List<Booking> response) {
                super.onNext(response);
                IGoerCartView view = getView();

                if (view != null) {
                    view.showMessage("Your cart was validated");
                }

                onOrderSent(response, true);
            }
        };
    }

    @Override
    public List<CartItemExtended> handleValidatedOrder(List<Booking> order) {
        List<CartItemExtended> cartNew = new ArrayList<>();

        for (Booking booking : order) {
            for (Table table : booking.getTables()) {
                if (!table.getBooked().isEmpty()) {
                    CartItemExtended cartItem = new CartItemExtended();

                    cartItem.setAmount(Integer.parseInt(table.getBooked()));
                    cartItem.setPartyName(booking.getPartyName());
                    cartItem.setTypeOfItem(CartItemExtended.Type.Table);
                    cartItem.setPrice(table.getPrice());
                    cartItem.setFullPrice(Double.parseDouble(cartItem.getPrice()) * cartItem.getAmount());
                    cartItem.setTitle(table.getType());

                    cartNew.add(cartItem);
                }
            }

            for (Bottle bottle : booking.getBottles()) {
                if (!bottle.getBooked().isEmpty()) {
                    CartItemExtended cartItem = new CartItemExtended();

                    cartItem.setAmount(Integer.parseInt(bottle.getBooked()));
                    cartItem.setPartyName(booking.getPartyName());
                    cartItem.setTypeOfItem(CartItemExtended.Type.Bottle);
                    cartItem.setPrice(bottle.getPrice());
                    cartItem.setFullPrice(Double.parseDouble(cartItem.getPrice()) * cartItem.getAmount());
                    cartItem.setTitle(bottle.getType());

                    cartNew.add(cartItem);
                }
            }
        }

        return cartNew;
    }

    @Override
    public void compilePaymentsForPayPal(List<Transaction> order) {
        HashMap<String, PayPalReceiverDetails> receivers = new HashMap<>();
        for (Transaction t : order) {
            PayPalReceiverDetails receiverDetails = new PayPalReceiverDetails();

            receiverDetails.setRecipient(t.getBillingEmail());
            receiverDetails.setSubtotal(new BigDecimal(t.getSubtotal()));

            if (receivers.containsKey(t.getBillingEmail())) {
                PayPalReceiverDetails detailsTemp = receivers.get(t.getBillingEmail());

                receivers.get(t.getBillingEmail()).setSubtotal(new BigDecimal(
                        detailsTemp.getSubtotal().doubleValue() + t.getSubtotal()));
            } else {
                receivers.put(t.getBillingEmail(), receiverDetails);
            }
        }

        lastTransactions = order;

        PayPalAdvancedPayment payment = new PayPalAdvancedPayment();

        payment.setReceivers(new ArrayList<>(receivers.values()));
        payment.setCurrencyType("USD");
        handlePayment(payment);
    }

    @Override
    public void handlePayment(PayPalAdvancedPayment payment) {
        getView().handlePayment(payment);
    }

    @Override
    public void sendConfirmation() {
        confirmationUseCase.setBookings(lastBookings);
        confirmationUseCase.setTransactions(lastTransactions);
        confirmationUseCase.execute(getConfirmationSubscriber());
    }

}

