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

        RelativeLayout container = (RelativeLayout) findViewById(R.id.activity_launch_screen);

        animationDrawable = (AnimationDrawable) container.getBackground();
        animationDrawable.setEnterFadeDuration(6000);
        animationDrawable.setExitFadeDuration(2000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (animationDrawable != null && !animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }

    @Override
    public void onClick(View v) {
        activityNavigator.startWelcomeScreenActivity(this);
    }
}
