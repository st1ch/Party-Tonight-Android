package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.fastaccess.datetimepicker.DatePickerFragmentDialog;
import com.fastaccess.datetimepicker.DateTimeBuilder;
import com.fastaccess.datetimepicker.callback.DatePickerCallback;
import com.fastaccess.datetimepicker.callback.TimePickerCallback;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Calendar;
import java.util.Locale;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.StringUtils;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateEventActivity extends AppCompatActivity implements DatePickerCallback, TimePickerCallback {

    public static final int PLACE_PICKER = 1;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_events_create);
        ButterKnife.bind(this);

        configureViews();
    }

    @OnClick(R.id.bLocation)
    public void getLocation() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            Intent i = builder.build(this);

            startActivityForResult(i, PLACE_PICKER);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.bDateAndTime)
    public void getDateAndTime() {
        Calendar today = Calendar.getInstance();

        long todayInMillis = today.getTimeInMillis();

        today.add(Calendar.YEAR, 1);

        long todayInYear = today.getTimeInMillis();

        DatePickerFragmentDialog dg = DatePickerFragmentDialog.newInstance(
                DateTimeBuilder.get()
                        .withTime(true)
                        .with24Hours(true)
                        .withSelectedDate(todayInMillis)
                        .withMinDate(todayInMillis)
                        .withMaxDate(todayInYear)
                        .withCurrentHour(12)
                        .withCurrentMinute(0));

        dg.show(getSupportFragmentManager(), "DatePickerFragmentDialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PLACE_PICKER:
                if (resultCode == Activity.RESULT_OK) {
                    Place place = PlacePicker.getPlace(this, data);

                    bLocation.setText(place.getAddress());
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

    @Override
    public void onDateSet(long date) {
    }

    @Override
    public void onTimeSet(long timeOnly, long dateWithTime) {
        String dateAsString = "";

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateWithTime);

        dateAsString += StringUtils.getMonth(cal.get(Calendar.MONTH));
        dateAsString += " ";
        dateAsString += cal.get(Calendar.DAY_OF_MONTH);
        dateAsString += " at ";

        dateAsString += String.format(Locale.getDefault(), "%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));

        bDateAndTime.setText(dateAsString);
    }
}
