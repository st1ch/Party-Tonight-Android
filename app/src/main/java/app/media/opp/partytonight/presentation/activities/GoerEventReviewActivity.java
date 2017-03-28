package app.media.opp.partytonight.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.RatingBar;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.Review;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.presenters.GoerReviewPresenter;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import app.media.opp.partytonight.presentation.views.IGoerReviewView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoerEventReviewActivity extends ProgressActivity implements IGoerReviewView {

    public static final String EVENT = "event";


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rbRating)
    RatingBar rbRating;

    @BindView(R.id.etContent)
    EditText etContent;

    @Inject
    GoerReviewPresenter presenter;

    private Event event;

    public static Intent launchIntent(Context context, Event event) {
        Intent intent = new Intent(context, GoerEventReviewActivity.class);
        intent.putExtra(EVENT, event);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goer_event_review);
        ButterKnife.bind(this);

        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);

        event = (Event) getIntent().getSerializableExtra(EVENT);

        PartyTonightApplication.getApp(this).getUserComponent().inject(this);

        presenter.onCreate(this);
    }

    @OnClick(R.id.btnSubmit)
    public void onClickSubmit() {
        presenter.sendReview(compileReview());
    }

    @OnClick(R.id.btnCancel)
    public void onClickCancel() {
        finish();
    }

    private Review compileReview() {
        Review review = new Review();

        review.setContent(etContent.getText().toString());
        review.setId_event(event.getIdEvent());
        review.setRating((int) rbRating.getRating());

        return review;
    }
}
