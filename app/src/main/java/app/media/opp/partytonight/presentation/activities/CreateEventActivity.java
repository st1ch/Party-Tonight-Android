package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Bottle;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.Table;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.presenters.AddEventPresenter;
import app.media.opp.partytonight.presentation.utils.StringUtils;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import app.media.opp.partytonight.presentation.views.IAddEventView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//public class CreateEventActivity extends ProgressActivity implements IAddEventView, View.OnClickListener {
public class CreateEventActivity extends AppCompatActivity implements DatePickerCallback,
        TimePickerCallback,
        IAddEventView,
        View.OnClickListener {

    public static final int PLACE_PICKER = 1;
    public static final String EVENT = "event";

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
    Event event;

    private long eventTime = 0;
    private String eventLocation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_events_create);
        ButterKnife.bind(this);

        PartyTonightApplication.getApp(this).getUserComponent().inject(this);
        configureViews();
        bCreate.setOnClickListener(this);
        restoreEventState(savedInstanceState);
        presenter.onCreate(this);
    }

    private void restoreEventState(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            event = new Event();
            addMoreBottles();
            addMoreTables();
        } else {
            event = (Event) savedInstanceState.getSerializable(EVENT);
            for (int i = 0; i < event.getBottles().size(); i++) {
                ViewGroup viewGroup = addBottle(i);
//                Log.e("children", "" + (viewGroup == llBottles) + " " + viewGroup.getClass() + " " + viewGroup.getChildAt(0).getClass());
                Bottle bottle = event.getBottles().get(i);
                bindBottle(viewGroup, bottle);
            }
            for (int i = 0; i < event.getTables().size(); i++) {
                ViewGroup viewGroup = addTable(i);
                Table table = event.getTables().get(i);
                bindTable(viewGroup, table);
            }
//                llBottles.findViewWithTag()

        }
    }

    private void bindTable(ViewGroup viewGroup, Table table) {
        ((EditText) viewGroup.getChildAt(0)).setText(table.getType());
        float price = table.getPrice();
        if (price != 0)
            ((EditText) viewGroup.getChildAt(1)).setText(String.valueOf(price));
        int quantity = table.getQuantity();
        if (quantity != 0)
            ((EditText) viewGroup.getChildAt(2)).setText(String.valueOf(quantity));
    }

    private void bindBottle(ViewGroup viewGroup, Bottle bottle) {
        ((EditText) viewGroup.getChildAt(0)).setText(bottle.getType());
        float price = bottle.getPrice();
        if (price != 0)
            ((EditText) viewGroup.getChildAt(1)).setText(String.valueOf(bottle.getPrice()));
        int quantity = bottle.getQuantity();
        if (quantity != 0)
            ((EditText) viewGroup.getChildAt(2)).setText(String.valueOf(bottle.getQuantity()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        List<Bottle> bottles = event.getBottles();
        for (int i = 0; i < llBottles.getChildCount(); i++) {
            ViewGroup bottleView = (ViewGroup) llBottles.getChildAt(i);
            String type = ((EditText) bottleView.getChildAt(0)).getText().toString();
            String priceString = ((EditText) bottleView.getChildAt(1)).getText().toString();
            float price = 0;
            if (!priceString.isEmpty()) price = Float.valueOf(priceString);
            String quantityString = ((EditText) bottleView.getChildAt(2)).getText().toString();
            int quantity = 0;
            if (!quantityString.isEmpty()) quantity = Integer.valueOf(quantityString);
            Bottle bottle = bottles.get(i);
            bottle.setType(type);
            bottle.setPrice(price);
            bottle.setQuantity(quantity);
        }

        List<Table> tables = event.getTables();
        for (int i = 0; i < llTables.getChildCount(); i++) {
            ViewGroup tableView = (ViewGroup) llTables.getChildAt(i);
            String type = ((EditText) tableView.getChildAt(0)).getText().toString();
            String priceString = ((EditText) tableView.getChildAt(1)).getText().toString();
            float price = 0;
            if (!priceString.isEmpty()) price = Float.valueOf(priceString);
            String quantityString = ((EditText) tableView.getChildAt(2)).getText().toString();
            int quantity = 0;
            if (!quantityString.isEmpty()) quantity = Integer.valueOf(quantityString);
            Table table = tables.get(i);
            table.setType(type);
            table.setPrice(price);
            table.setQuantity(quantity);
        }

        outState.putSerializable(EVENT, event);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        presenter.onRelease();
        super.onDestroy();
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
//                        .withTheme(R.style.DateAndTimePickers)
                        .withTime(true)
                        .with24Hours(true)
                        .withSelectedDate(todayInMillis)
                        .withMinDate(todayInMillis)
                        .withMaxDate(todayInYear)
                        .withCurrentHour(12)
                        .withCurrentMinute(0));

        dg.show(getSupportFragmentManager(), "DatePickerFragmentDialog");
    }

    @OnClick(R.id.bAddBottle)
    public void addMoreBottles() {
        addBottle(event.getBottles().size());
        event.getBottles().add(new Bottle());

    }

    private ViewGroup addBottle(int bottleNumber) {
        ViewGroup inflate = (ViewGroup) getLayoutInflater().inflate(R.layout.bottle_layout, llBottles);
        String tag = "b" + bottleNumber;
        ViewGroup viewGroup = (ViewGroup) inflate.getChildAt(bottleNumber);
        viewGroup.setTag(tag);
        return viewGroup;
    }

    @OnClick(R.id.bAddTable)
    public void addMoreTables() {
        addTable(event.getTables().size());
        event.getTables().add(new Table());
    }

    private ViewGroup addTable(int tableNumber) {
        ViewGroup inflate = (ViewGroup) getLayoutInflater().inflate(R.layout.table_layout, llTables);
        String tag = "t" + tableNumber;
        ViewGroup viewGroup = (ViewGroup) inflate.getChildAt(tableNumber);
        viewGroup.setTag(tag);
        return viewGroup;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PLACE_PICKER:
                if (resultCode == Activity.RESULT_OK) {
                    Place place = PlacePicker.getPlace(this, data);
                    LatLng latLng = place.getLatLng();

                    bLocation.setText(place.getAddress());
                    eventLocation = latLng.latitude + " " + latLng.longitude;
                }
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void configureViews() {
        bLocation.setSelected(true);

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

        eventTime = dateWithTime;
    }

    @Override
    public void navigateBack() {
        onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bCreate:
                //TODO set fields of event
                presenter.onAddButtonClick(event);
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String message) {

    }
}
