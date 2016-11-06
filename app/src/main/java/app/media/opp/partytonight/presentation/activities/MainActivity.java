package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;

public class MainActivity extends Activity implements View.OnClickListener {

    private ActivityNavigator activityNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_main);
        findViewById(R.id.bPromoterAddEvent).setOnClickListener(this);
        findViewById(R.id.bPromoterMyEvents).setOnClickListener(this);
        activityNavigator = new ActivityNavigator();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bPromoterAddEvent:
                activityNavigator.startPromoterCreateEventActivity(this);
                break;
            case R.id.bPromoterMyEvents:
                activityNavigator.startPromoterEventsActivity(this);
                break;
        }
    }
}
