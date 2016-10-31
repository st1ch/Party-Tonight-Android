package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;

public class WelcomeScreenActivity extends Activity implements View.OnClickListener {

    AnimationDrawable animationDrawable;
    private ActivityNavigator activityNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        findViewById(R.id.bPromoter).setOnClickListener(this);
        findViewById(R.id.bPartyGoer).setOnClickListener(this);
        activityNavigator = new ActivityNavigator();

        configureAnimation(R.id.activity_welcome_screen);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bPartyGoer:
                break;
            case R.id.bPromoter:
                activityNavigator.startPromoterSignInActivity(this);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        stopGradientAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();

        startGradientAnimation();
    }

    /**
     * Setting up the animation
     *
     * @param rootViewId res.id of root view with animation drawable as background
     */
    private void configureAnimation(int rootViewId) {
        RelativeLayout container = (RelativeLayout) findViewById(rootViewId);

        if (container != null) {
            animationDrawable = (AnimationDrawable) container.getBackground();
            animationDrawable.setEnterFadeDuration(6000);
            animationDrawable.setExitFadeDuration(2000);
        }
    }

    /**
     * Starts background animation
     * Need to be called in onResume
     */
    private void startGradientAnimation() {
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    /**
     * Stops background animation
     * Need to be called in onPause
     */
    private void stopGradientAnimation() {
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }
}
