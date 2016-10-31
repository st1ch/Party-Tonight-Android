package app.media.opp.partytonight.presentation.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;

public class LaunchScreenActivity extends AppCompatActivity implements View.OnClickListener {


    AnimationDrawable animationDrawable;
    private ActivityNavigator activityNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        findViewById(R.id.bGetStarted).setOnClickListener(this);
        activityNavigator = new ActivityNavigator();

        configureAnimation(R.id.activity_launch_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        startGradientAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();

        stopGradientAnimation();
    }

    @Override
    public void onClick(View v) {
        activityNavigator.startWelcomeScreenActivity(this);
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
