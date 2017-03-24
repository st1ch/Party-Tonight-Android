package app.media.opp.partytonight.presentation;

import android.app.Application;
import android.content.Context;

import com.paypal.android.MEP.PayPal;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.data.di.AccountModule;
import app.media.opp.partytonight.data.di.UserComponent;
import app.media.opp.partytonight.di.ApplicationComponent;
import app.media.opp.partytonight.di.ApplicationModule;
import app.media.opp.partytonight.di.DaggerApplicationComponent;

/**
 * Created by sebastian on 14.06.16.
 */
public class PartyTonightApplication extends Application {

    private static PayPal payPalClient;
    private ApplicationComponent component;
    private UserComponent userComponent;
    private boolean payPalClientLoaded = false;

    public static PartyTonightApplication getApp(Context context) {
        return (PartyTonightApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildAppComponent();
        buildUserComponent();

        buildPayPalClient(payPalClient);
    }

    private void buildAppComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return component;
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }

    public void logout() {
        //TODO логаут здесь
    }

    private void buildPayPalClient(PayPal payPalClient) {
        payPalClient = PayPal.getInstance();

        if (payPalClient == null) {
            payPalClient = PayPal.initWithAppID(this, getString(R.string.paypal_client_id), PayPal.ENV_SANDBOX);

            payPalClient.setLanguage("en_US");

            payPalClient.setFeesPayer(PayPal.FEEPAYER_EACHRECEIVER);

            payPalClient.setShippingEnabled(true);

            payPalClientLoaded = true;
        }
    }

    private void buildUserComponent() {
        userComponent = component.plus(new AccountModule());
    }
}
