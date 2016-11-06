package app.media.opp.partytonight.presentation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.presenters.AddEventPresenter;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import app.media.opp.partytonight.presentation.views.IAddEventView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateEventActivity extends ProgressActivity implements IAddEventView, View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etClubName)
    EditText etClubName;
    @BindView(R.id.etClubCapacity)
    EditText etClubCapacity;
    @BindView(R.id.etTicketPrice)
    EditText etTicketPrice;
    @BindView(R.id.etPartyName)
    EditText etPartyName;
    @BindView(R.id.llBottles)
    LinearLayout llBottles;
    @BindView(R.id.llTables)
    LinearLayout llTables;
    @BindView(R.id.bDateAndTime)
    Button bDateAndTime;
    @BindView(R.id.bLocation)
    Button bLocation;
    @BindView(R.id.bAddPhoto)
    Button bAddPhoto;
    @BindView(R.id.bAddBottle)
    Button bAddBottle;
    @BindView(R.id.bAddTable)
    Button bAddTable;
    @BindView(R.id.bCreate)
    Button bCreate;
    @Inject
    AddEventPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_events_create);
        ButterKnife.bind(this);
        PartyTonightApplication.getApp(this).getUserComponent().inject(this);
        configureViews();
        bCreate.setOnClickListener(this);
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
    public void navigateBack() {
        onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bCreate:
                Event event = new Event();
                //TODO set fields of event
                presenter.onAddButtonClick(event);
                break;
        }
    }
}
