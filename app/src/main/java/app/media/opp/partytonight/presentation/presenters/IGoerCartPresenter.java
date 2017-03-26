package app.media.opp.partytonight.presentation.presenters;

import com.paypal.android.MEP.PayPalAdvancedPayment;

import java.util.List;

import app.media.opp.partytonight.domain.Booking;
import app.media.opp.partytonight.domain.CartItemExtended;
import app.media.opp.partytonight.domain.Transaction;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 3/24/17
 */
public interface IGoerCartPresenter {

    void onOrderSent(List<Booking> order);

    void compilePaymentsForPayPal(List<Transaction> order);

    void handlePayment(PayPalAdvancedPayment payment);

    List<CartItemExtended> handleValidatedOrder(List<Booking> order);

    void sendConfirmation();

    void validateOrder(List<Booking> order);
}
