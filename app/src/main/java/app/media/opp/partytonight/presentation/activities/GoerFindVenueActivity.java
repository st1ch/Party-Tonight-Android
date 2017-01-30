package app.media.opp.partytonight.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.app.view.SearchView;
import app.media.opp.partytonight.presentation.presenters.GoerFindVenuePresenter;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import app.media.opp.partytonight.presentation.views.IGoerFindVenueView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 1/30/17
 */

public class GoerFindVenueActivity extends ProgressActivity implements IGoerFindVenueView {

    public static final String EVENT = "event";
    @BindView(R.id.svVenue)
    SearchView svVenue;
    @Inject
    GoerFindVenuePresenter presenter;
    private ActivityNavigator activityNavigator;

    public static Intent launchIntent(Context context, @NonNull Event event) {
        Intent intent = new Intent(context, EventScreenActivity.class);
        intent.putExtra(EVENT, event);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goer_find_venue);
        ButterKnife.bind(this);
        PartyTonightApplication.getApp(this).getUserComponent().inject(this);
        configureViews();
        activityNavigator = new ActivityNavigator();

        presenter.onCreate(this);
    }

    private void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this,
                (Toolbar) findViewById(R.id.toolbar), true);


    }

    @Override
    protected void onDestroy() {
        presenter.onRelease();
        super.onDestroy();
    }

    @Override
    public void renderList(List<Event> response) {

    }
}
