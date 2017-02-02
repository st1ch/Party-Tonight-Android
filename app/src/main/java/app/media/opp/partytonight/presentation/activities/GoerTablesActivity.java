package app.media.opp.partytonight.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 2/2/17
 */

public class GoerTablesActivity extends AppCompatActivity {

    public static final String EVENT = "event";
    private final ActivityNavigator navigator = new ActivityNavigator();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rvTables)
    RecyclerView rvTables;

    @BindView(R.id.tvTime)
    TextView tvTime;

    private Event event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goer_event_details);
        ButterKnife.bind(this);

        configureViews();
        event = (Event) getIntent().getSerializableExtra(EVENT);

//        rvEvents.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new EventsAdapter(this);
//        adapter.setListener((position, event) ->
//                navigator.startEventScreenActivity(PromoterEventsActivity.this, event)
//        );
//        rvEvents.setAdapter(adapter);
//        presenter.onCreate(this);
    }

    public void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true, true);

    }
}
