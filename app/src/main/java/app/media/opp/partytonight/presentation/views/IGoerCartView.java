package app.media.opp.partytonight.presentation.views;

import android.content.Context;

import com.paypal.android.MEP.PayPalAdvancedPayment;

import java.util.HashMap;

import app.media.opp.partytonight.domain.booking.Booking;

public interface IGoerCartView extends IProgressView {
    Context getContext();

    void handlePayment(PayPalAdvancedPayment payment);

    void handleValidatedCart(HashMap<Integer, Booking> cart);
}
