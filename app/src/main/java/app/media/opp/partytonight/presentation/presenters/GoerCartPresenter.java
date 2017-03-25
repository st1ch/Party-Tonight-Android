package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.paypal.android.MEP.PayPalAdvancedPayment;
import com.paypal.android.MEP.PayPalReceiverDetails;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.Booking;
import app.media.opp.partytonight.domain.Transaction;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.GoerCartConfirmUseCase;
import app.media.opp.partytonight.domain.usecase.GoerCartUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.IGoerCartView;
import okhttp3.ResponseBody;

@UserScope
public class GoerCartPresenter extends ProgressPresenter<IGoerCartView> implements IGoerCartPresenter {
    List<Transaction> lastTransactions = new ArrayList<>();
    List<Booking> lastBookings = new ArrayList<>();
    private GoerCartUseCase useCase;
    private GoerCartConfirmUseCase confirmationUseCase;

    @Inject
    public GoerCartPresenter(Messages messages, GoerCartUseCase useCase, GoerCartConfirmUseCase confirmationUseCase) {
        super(messages);
        this.useCase = useCase;
        this.confirmationUseCase = confirmationUseCase;
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
    }

    @Override
    public void onOrderSent(List<Booking> order) {
        lastBookings = order;

        useCase.setOrder(order);
        useCase.execute(getTransactionsSubscriber());
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

    @Override
    public void compilePaymentsForPayPal(List<Transaction> order) {
        lastTransactions = order;

        ArrayList<PayPalReceiverDetails> receivers = new ArrayList<>(order.size());

        for (Transaction t : order) {
            PayPalReceiverDetails receiverDetails = new PayPalReceiverDetails();

            receiverDetails.setRecipient(t.getBillingEmail());
            receiverDetails.setSubtotal(new BigDecimal(t.getSubtotal()));

            receivers.add(receiverDetails);
        }

        PayPalAdvancedPayment payment = new PayPalAdvancedPayment();

        payment.setReceivers(receivers);
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
