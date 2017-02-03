package app.media.opp.partytonight.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.adapters.GoerEventsAdapter;
import app.media.opp.partytonight.presentation.app.view.SearchView;
import app.media.opp.partytonight.presentation.presenters.GoerFindVenuePresenter;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import app.media.opp.partytonight.presentation.views.IGoerFindVenueView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 1/30/17
 */

public class GoerFindVenueActivity extends ProgressActivity implements IGoerFindVenueView {

    public static final String EVENT = "event";

    @BindView(R.id.svVenue)
    SearchView svVenue;

    @BindView(R.id.tvEmptyList)
    TextView tvEmptyList;

    @BindView(R.id.rvEvents)
    RecyclerView rvEvents;

    @Inject
    GoerFindVenuePresenter presenter;
    GoerEventsAdapter adapter;
    private ActivityNavigator navigator = new ActivityNavigator();

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

        presenter.onCreate(this);
    }

    private void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this,
                (Toolbar) findViewById(R.id.toolbar), true, true);

        rvEvents.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GoerEventsAdapter(this);
        adapter.setListener((position, event) ->
                navigator.startGoerEventActivity(this, event)
        );
        rvEvents.setAdapter(adapter);

        svVenue.setFindOnClickListener(view -> {
            hideKeyboard();
            presenter.onFindButtonClick(svVenue.getText());
        });
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @OnClick(R.id.btnCart)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCart:
                navigator.startGoerCartActivity(this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onRelease();
        super.onDestroy();
    }

    @Override
    public void renderList(List<Event> response) {
        tvEmptyList.setVisibility(View.GONE);
        adapter.setData(response);
    }

    @Override
    public void cleanList() {
        adapter.clear();
    }

    @Override
    public void emptyResponse() {
        tvEmptyList.setVisibility(View.VISIBLE);
    }
}

