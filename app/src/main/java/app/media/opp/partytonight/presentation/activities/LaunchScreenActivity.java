package app.media.opp.partytonight.presentation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;

public class LaunchScreenActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityNavigator activityNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        findViewById(R.id.bGetStarted).setOnClickListener(this);
        activityNavigator = new ActivityNavigator();
    }

    @Override
    public void onClick(View v) {
        activityNavigator.startWelcomeScreenActivity(this);
    }
}
