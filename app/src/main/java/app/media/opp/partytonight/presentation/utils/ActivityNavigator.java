package app.media.opp.partytonight.presentation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.activities.CreateEventActivity;
import app.media.opp.partytonight.presentation.activities.EventDetailsContainerActivity;
import app.media.opp.partytonight.presentation.activities.EventScreenActivity;
import app.media.opp.partytonight.presentation.activities.MainActivity;
import app.media.opp.partytonight.presentation.activities.PromoterEventsActivity;
import app.media.opp.partytonight.presentation.activities.PromoterSignInActivity;
import app.media.opp.partytonight.presentation.activities.PromoterSignUpActivity;
import app.media.opp.partytonight.presentation.activities.WelcomeScreenActivity;

/**
 * Created by arkadii on 10/30/16.
 */

public class ActivityNavigator {

    public ActivityNavigator() {
    }

    public void startWelcomeScreenActivity(Activity mActivityContext) {
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

    public void startPromoterSignUpActivity(Context mActivityContext, int animationFrame) {
        final String extraTag = "AnimationFrame";

        Intent intent = new Intent(mActivityContext, PromoterSignUpActivity.class);

        intent.putExtra(extraTag, animationFrame);

        mActivityContext.startActivity(intent);
    }

    public void startMainActivity(Activity mActivityContext, boolean inNewTask) {
        Intent intent = new Intent(mActivityContext, MainActivity.class);
        if (inNewTask) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        mActivityContext.startActivity(intent);
        if (!inNewTask) {
            mActivityContext.finish();
        }
    }


    public void startPromoterSignInActivity(Context mActivityContext, int animationFrame) {
        final String extraTag = "AnimationFrame";

        Intent intent = new Intent(mActivityContext, PromoterSignInActivity.class);

        intent.putExtra(extraTag, animationFrame);

        mActivityContext.startActivity(intent);
    }

    public void startWelcomeScreenActivity(Activity mActivityContext, int animationFrame) {
        final String extraTag = "AnimationFrame";

        Intent intent = new Intent(mActivityContext, WelcomeScreenActivity.class);

        intent.putExtra(extraTag, animationFrame);

        mActivityContext.startActivity(intent);
        mActivityContext.finish();
    }

    public void startEventScreenActivity(Activity activity, Event event) {
        activity.startActivity(EventScreenActivity.launchIntent(activity, event));
    }

    public void startPromoterCreateEventActivity(Context context) {
        Intent intent = new Intent(context, CreateEventActivity.class);
        context.startActivity(intent);
    }

    public void startPromoterEventsActivity(Context context) {
        Intent intent = new Intent(context, PromoterEventsActivity.class);
        context.startActivity(intent);
    }

    public void startBottleScreen(Activity activity, Event event) {
        Log.e("ActivityNavigator", "startBottle");
        Intent intent = EventDetailsContainerActivity.launchIntent(activity,
                EventDetailsContainerActivity.BOTTLES, event);
        activity.startActivity(intent);
    }

    public void startTableScreen(Activity activity, Event event) {
        Intent intent = EventDetailsContainerActivity.launchIntent(activity,
                EventDetailsContainerActivity.TABLES, event);
        activity.startActivity(intent);
    }

    public void startStatementTotalScreen(Activity activity, Event event) {
        Intent intent = EventDetailsContainerActivity.launchIntent(activity,
                EventDetailsContainerActivity.STATEMENT, event);
        activity.startActivity(intent);
    }
}