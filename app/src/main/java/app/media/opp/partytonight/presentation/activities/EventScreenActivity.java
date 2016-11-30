package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.app.view.EventDetailsItem;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventScreenActivity extends AppCompatActivity {
    @BindView(R.id.ediDoorRevenue)
    EventDetailsItem ediDoorRevenue;
    @BindView(R.id.ediBottles)
    EventDetailsItem ediBottles;
    @BindView(R.id.ediTables)
    EventDetailsItem ediTables;
    @BindView(R.id.ediStatementTotal)
    EventDetailsItem ediStatementTotal;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ActivityNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_event_details);
        ButterKnife.bind(this);
        navigator = new ActivityNavigator();
        configureViews();
    }

    public void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);
    }


    @OnClick({R.id.ediBottles, R.id.ediTables, R.id.ediStatementTotal})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ediBottles:
                navigator.startBottleScreen(this);
                break;
            case R.id.ediTables:
                navigator.startTableScreen(this);
                break;
            case R.id.ediStatementTotal:
                navigator.startStatementTotalScreen(this);
                break;
        }
    }
}
