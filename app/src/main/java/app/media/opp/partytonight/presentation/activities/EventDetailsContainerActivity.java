package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.fragments.BottleScreenFragment;
import app.media.opp.partytonight.presentation.fragments.StatementTotalFragment;
import app.media.opp.partytonight.presentation.fragments.TableScreenFragment;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;

public class EventDetailsContainerActivity extends AppCompatActivity {
    public static final int BOTTLES = 1;
    public static final int TABLES = 2;
    public static final int STATEMENT = 3;
    public static final String WHAT = "what";
    public static final String EVENT = "event";

    public static Intent launchIntent(Context context, int what, Event event) {
        Intent intent = new Intent(context, EventDetailsContainerActivity.class);
        intent.putExtra(WHAT, what);
        intent.putExtra(EVENT, event);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details_container);
        configureViews();

    }

    public void configureViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);

        Intent intent = getIntent();
        int what = intent.getIntExtra(WHAT, 0);
        Event event = (Event) intent.getSerializableExtra(EVENT);
        Fragment fragment = null;
        switch (what) {
            case BOTTLES:
                fragment = BottleScreenFragment.newInstance(event);
                break;
            case TABLES:
                fragment = TableScreenFragment.newInstance(event);
                break;
            case STATEMENT:
                fragment = StatementTotalFragment.newInstance(event);
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
