package app.media.opp.partytonight.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.Revenue;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.app.view.EventDetailsItem;
import app.media.opp.partytonight.presentation.presenters.EventScreenPresenter;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import app.media.opp.partytonight.presentation.views.IEventScreenView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventScreenActivity extends ProgressActivity implements IEventScreenView {
    public static final String EVENT = "event";
    public static final String REVENUE = "revenue";
    private final ActivityNavigator navigator = new ActivityNavigator();
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
    @Inject
    EventScreenPresenter presenter;
    private Event event;
    private Revenue revenue;

    public static Intent launchIntent(Context context, @NonNull Event event) {
        Intent intent = new Intent(context, EventScreenActivity.class);
        intent.putExtra(EVENT, event);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_event_details);
        ButterKnife.bind(this);
        PartyTonightApplication.getApp(this).getUserComponent().inject(this);
        configureViews();
        event = (Event)getIntent().getSerializableExtra(EVENT);
        if (savedInstanceState != null) {
            revenue = (Revenue) savedInstanceState.getSerializable(REVENUE);
        }

        presenter.onCreate(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onRelease();
        super.onDestroy();
    }

    public void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);
    }


    @OnClick({R.id.ediBottles, R.id.ediTables, R.id.ediStatementTotal})
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ediBottles:
                navigator.startBottleScreen(this, event);
                break;
            case R.id.ediTables:
                navigator.startTableScreen(this, event);
                break;
            case R.id.ediStatementTotal:
                navigator.startStatementTotalScreen(this, event);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(REVENUE, revenue);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void renderRevenue(Revenue revenue) {
        if (revenue == null) {
            revenue = new Revenue("0");
        }

        this.revenue = revenue;
        ediDoorRevenue.setLabel(ediDoorRevenue.getLabel() + "$" + revenue.getRevenue());
    }

    @Override
    public Event getEvent() {
        return event;
    }

    @Override
    public Revenue getRevenue() {
        return revenue;
    }
}
