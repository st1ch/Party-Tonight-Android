package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateEventActivity extends AppCompatActivity {

    public static final String INTENT_LONGITUDE = "intent_longitude";
    public static final String INTENT_LATITUDE = "intent_latitude";

    public static final String INTENT_ADDRESS_LINE = "intent_address_line";
    private static final int REQUEST_LOCATION = 1;
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
    private double locationLongitude = -1;
    private double locationLatitude = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_events_create);
        ButterKnife.bind(this);

        configureViews();
    }

    @OnClick(R.id.bLocation)
    public void getLocation() {
        Intent intent = new Intent(this, PromoterLocationActivity.class);

        intent.putExtra(INTENT_LATITUDE, locationLatitude);
        intent.putExtra(INTENT_LONGITUDE, locationLongitude);

        startActivityForResult(intent, REQUEST_LOCATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_LOCATION:
                if (resultCode == Activity.RESULT_OK) {
                    bLocation.setText(data.getStringExtra(INTENT_ADDRESS_LINE));


                    locationLatitude = data.getDoubleExtra(INTENT_LATITUDE, locationLatitude);
                    locationLongitude = data.getDoubleExtra(INTENT_LONGITUDE, locationLongitude);
                }
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);
    }
}
