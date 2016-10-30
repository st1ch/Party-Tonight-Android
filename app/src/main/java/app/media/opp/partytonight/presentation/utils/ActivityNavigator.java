package app.media.opp.partytonight.presentation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import app.media.opp.partytonight.presentation.activities.MainActivity;
import app.media.opp.partytonight.presentation.activities.PromoterSignInActivity;
import app.media.opp.partytonight.presentation.activities.PromoterSignUpActivity;
import app.media.opp.partytonight.presentation.activities.WelcomeScreenActivity;

/**
 * Created by arkadii on 10/30/16.
 */

public class ActivityNavigator {

    public ActivityNavigator() {
    }

    public void startWelcomeScreenActivity(Activity mActivityContext){
        Intent intent = new Intent(mActivityContext, WelcomeScreenActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivityContext.startActivity(intent);
        mActivityContext.finish();
    }

    public void startPromoterSignInActivity(Context mActivityContext) {
        Intent intent = new Intent(mActivityContext, PromoterSignInActivity.class);
        mActivityContext.startActivity(intent);
    }

    public void startPromoterSignUpActivity(Context mActivityContext) {
        Intent intent = new Intent(mActivityContext, PromoterSignUpActivity.class);
        mActivityContext.startActivity(intent);
    }

    public void startMainActivity(Context mActivityContext) {
        Intent intent = new Intent(mActivityContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivityContext.startActivity(intent);
    }
}