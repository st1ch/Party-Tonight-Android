package app.media.opp.partytonight.presentation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.adapters.EventsAdapter;
import app.media.opp.partytonight.presentation.presenters.PromoterEventsPresenter;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import app.media.opp.partytonight.presentation.views.IEventListView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PromoterEventsActivity extends AppCompatActivity implements IEventListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvEvents)
    RecyclerView rvEvents;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @Inject
    PromoterEventsPresenter presenter;
    private EventsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_events);
        ButterKnife.bind(this);
        PartyTonightApplication.getApp(this).getUserComponent().inject(this);
        configureViews();
        rvEvents.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventsAdapter(this);
        rvEvents.setAdapter(adapter);
        presenter.onCreate(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onRelease();
        super.onDestroy();
    }

    public void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true, R.mipmap.ic_back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void renderList(List<Event> data) {
        adapter.setData(data);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        rvEvents.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        rvEvents.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
