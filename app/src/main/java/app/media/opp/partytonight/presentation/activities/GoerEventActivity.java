package app.media.opp.partytonight.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.booking.BookedTicket;
import app.media.opp.partytonight.domain.booking.Booking;
import app.media.opp.partytonight.presentation.fragments.CheckAgeFragment;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.StringUtils;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoerEventActivity extends AppCompatActivity {
    public static final String EVENT = "event";
    private final ActivityNavigator navigator = new ActivityNavigator();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.slThumbnail)
    SliderLayout slThumbnail;

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

        setTitle(event.getPartyName());

        configureViews();
    }

    public void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true, true);

        tvAddress.setText(event.getLocation());
        tvAddress.setSelected(true);
        tvAddress.setHorizontallyScrolling(true);
        tvTime.setText(StringUtils.getDate(event.getTime()));

        if (event.getPhotos() != null && !event.getPhotos().isEmpty()) {
            slThumbnail.setVisibility(View.VISIBLE);

            HashMap<String, String> url_maps = new HashMap<>();
            for (int i = 0; i < event.getPhotos().size(); i++) {
                url_maps.put(i + "", event.getPhotos().get(i));
            }

            for (String name : url_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(this);

                textSliderView
                        .description(name)
                        .image(url_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit);

                slThumbnail.addSlider(textSliderView);
            }

            slThumbnail.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            slThumbnail.setPresetTransformer(SliderLayout.Transformer.Default);

            slThumbnail.setDuration(4000);
        }
    }

    @OnClick({R.id.btnTables, R.id.btnBuyLiquor, R.id.btnRsvp, R.id.btnReviews, R.id.btnShare})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTables:
                navigator.startGoerTablesActivity(this, event);
                break;
            case R.id.btnBuyLiquor:
                checkAge();
                break;
            case R.id.btnRsvp:
                orderTicket();
                break;
            case R.id.btnReviews:

                break;
            case R.id.btnShare:

                break;
        }
    }

    private void orderTicket() {
        GoerCartActivity.putToCart(event.getIdEvent(), new Booking(event.getIdEvent(),
                new BookedTicket("", Integer.parseInt(event.getTicketPrice().get(0).getPrice()))));
    }

    private void checkAge() {
        CheckAgeFragment fragment = CheckAgeFragment.newInstance();

        Bundle bundle = new Bundle();
        bundle.putSerializable("event", event);

        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "can_buy_liquors");
    }

    @OnClick(R.id.btnCart)
    public void onCartClick() {
        navigator.startGoerCartActivity(this);
    }

}
