package app.media.opp.partytonight.presentation.views;

import android.content.Context;

import com.paypal.android.MEP.PayPalAdvancedPayment;

import java.util.List;

import app.media.opp.partytonight.domain.CartItemExtended;

public interface IGoerCartView extends IProgressView {
    Context getContext();

    void handlePayment(PayPalAdvancedPayment payment);

    void handleValidatedCart(List<CartItemExtended> cart);
}
