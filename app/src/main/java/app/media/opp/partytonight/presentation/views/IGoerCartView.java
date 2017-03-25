package app.media.opp.partytonight.presentation.views;

import android.content.Context;

import com.paypal.android.MEP.PayPalAdvancedPayment;

public interface IGoerCartView extends IProgressView {
    Context getContext();

    void handlePayment(PayPalAdvancedPayment payment);
}
