package app.media.opp.partytonight.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Account;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;

public class LaunchScreenActivity extends AppCompatActivity implements View.OnClickListener {


    @Inject
    Account account;
    private ActivityNavigator activityNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        findViewById(R.id.bGetStarted).setOnClickListener(this);

        activityNavigator = new ActivityNavigator();
        PartyTonightApplication.getApp(this).getUserComponent().inject(this);

        if (account.isAuthorized()) {
            if (account.isAuthorizedAsGoer()) {
                activityNavigator.startGoerMainActivity(this, false);
            } else {
                activityNavigator.startPromoterMainActivity(this, false);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        activityNavigator.startWelcomeScreenActivity(this);
    }
}
