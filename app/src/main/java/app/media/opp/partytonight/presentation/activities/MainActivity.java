package app.media.opp.partytonight.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityNavigator activityNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_main);
        findViewById(R.id.bPromoterAddEvent).setOnClickListener(this);
        findViewById(R.id.bPromoterMyEvents).setOnClickListener(this);
        activityNavigator = new ActivityNavigator();

        ToolbarUtils.configureToolbarAsActionBar(this,
                (Toolbar) findViewById(R.id.toolbar), false);
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
