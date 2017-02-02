package app.media.opp.partytonight.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.StringUtils;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 2/2/17
 */

public class GoerEventActivity extends AppCompatActivity {
    public static final String EVENT = "event";
    private final ActivityNavigator navigator = new ActivityNavigator();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvAddress)
    TextView tvAddress;

    @BindView(R.id.tvTime)
    TextView tvTime;

    private Event event;

    public static Intent launchIntent(Context context, @NonNull Event event) {
        Intent intent = new Intent(context, GoerEventActivity.class);
        intent.putExtra(EVENT, event);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goer_event_details);
        ButterKnife.bind(this);

        event = (Event) getIntent().getSerializableExtra(EVENT);

        configureViews();
    }

    public void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true, true);

        tvAddress.setText(event.getLocation());
        tvAddress.setSelected(true);
        tvAddress.setHorizontallyScrolling(true);
        tvTime.setText(StringUtils.getDate(event.getTime()));
    }

    @OnClick({R.id.btnTables, R.id.btnBuyLiquor, R.id.btnRsvp, R.id.btnReviews, R.id.btnShare})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTables:

                break;
            case R.id.btnBuyLiquor:

                break;
            case R.id.btnRsvp:

                break;
            case R.id.btnReviews:

                break;
            case R.id.btnShare:

                break;
        }
    }

}
