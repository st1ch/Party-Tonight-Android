package app.media.opp.partytonight.presentation.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.AnimationDrawableUtil;

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

        AnimationDrawableUtil.startGradientAnimation(animationDrawable);
    }

    @Override
    protected void onStop() {
        super.onStop();

        AnimationDrawableUtil.stopGradientAnimation(animationDrawable);
    }

    @Override
    public void onClick(View v) {
        activityNavigator.startWelcomeScreenActivity(this, AnimationDrawableUtil.getCurrentFrame(animationDrawable));
    }

    private void configureAnimation(int rootViewID) {
        animationDrawable = AnimationDrawableUtil.configureAnimation((ViewGroup) findViewById(rootViewID),
                6000, 2000);
    }
}
