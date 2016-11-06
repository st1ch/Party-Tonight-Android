package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;

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
}
