package app.media.opp.partytonight.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.fragments.GoerBuyBottleFragment;
import app.media.opp.partytonight.presentation.fragments.GoerBuyTableFragment;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 2/2/17
 */


public class GoerBuyItemActivity extends AppCompatActivity {
    public static final int BOTTLES = 1;
    public static final int TABLES = 2;
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
                fragment = GoerBuyBottleFragment.newInstance(event);
                break;
            case TABLES:
                fragment = GoerBuyTableFragment.newInstance(event);
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
