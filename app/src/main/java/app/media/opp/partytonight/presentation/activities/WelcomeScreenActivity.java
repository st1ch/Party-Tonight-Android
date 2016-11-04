package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.AnimationDrawableUtil;

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
                activityNavigator.startPromoterSignInActivity(this, AnimationDrawableUtil.getCurrentFrame(animationDrawable));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        AnimationDrawableUtil.stopGradientAnimation(animationDrawable);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AnimationDrawableUtil.startGradientAnimation(animationDrawable);
    }

    private void configureAnimation(int rootViewId) {
        final String extraTag = "AnimationFrame";

        animationDrawable = AnimationDrawableUtil.configureAnimation((ViewGroup) findViewById(rootViewId),
                6000, 2000);

        int frame = getIntent().getIntExtra(extraTag, 0);

        AnimationDrawableUtil.setAnimationFrame(animationDrawable, frame);
    }
}
