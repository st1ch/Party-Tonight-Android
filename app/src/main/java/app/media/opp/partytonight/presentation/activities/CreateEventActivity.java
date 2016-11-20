package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fastaccess.datetimepicker.DatePickerFragmentDialog;
import com.fastaccess.datetimepicker.DateTimeBuilder;
import com.fastaccess.datetimepicker.callback.DatePickerCallback;
import com.fastaccess.datetimepicker.callback.TimePickerCallback;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.data.Ticket;
import app.media.opp.partytonight.domain.Bottle;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.Table;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.dialogs.ProgressDialogFragment;
import app.media.opp.partytonight.presentation.presenters.AddEventPresenter;
import app.media.opp.partytonight.presentation.utils.StringUtils;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import app.media.opp.partytonight.presentation.views.IAddEventView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//public class CreateEventActivity extends ProgressActivity implements IAddEventView, View.OnClickListener {
public class CreateEventActivity extends ProgressActivity implements DatePickerCallback,
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
            if (event.getTime() > 0) {
                renderFormattedDate(event.getTime());
            }
            if (event.getLocation() != null) {
                bLocation.setText(event.getLocation());
            }
            for (int i = 0; i < event.getBottles().size(); i++) {
                ViewGroup viewGroup = addBottle(i);
                Bottle bottle = event.getBottles().get(i);
                bindBottle(viewGroup, bottle);
            }
            for (int i = 0; i < event.getTables().size(); i++) {
                ViewGroup viewGroup = addTable(i);
                Table table = event.getTables().get(i);
                bindTable(viewGroup, table);
            }
        }
    }

    private void bindTable(ViewGroup viewGroup, Table table) {
        ((EditText) viewGroup.getChildAt(0)).setText(table.getType());
        ((EditText) viewGroup.getChildAt(1)).setText(table.getPrice());
        ((EditText) viewGroup.getChildAt(2)).setText(table.getAvailable());
    }

    private void bindBottle(ViewGroup viewGroup, Bottle bottle) {
        ((EditText) viewGroup.getChildAt(0)).setText(bottle.getType());
        ((EditText) viewGroup.getChildAt(1)).setText(bottle.getPrice());
        ((EditText) viewGroup.getChildAt(2)).setText(bottle.getAvailable());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        updateEventBottlesAndTables();

        outState.putSerializable(EVENT, event);
        super.onSaveInstanceState(outState);
    }

    private void updateEventBottlesAndTables() {
        List<Bottle> bottles = event.getBottles();
        for (int i = 0; i < llBottles.getChildCount(); i++) {
            ViewGroup bottleView = (ViewGroup) llBottles.getChildAt(i);
            String type = ((EditText) bottleView.getChildAt(0)).getText().toString();
            String priceString = ((EditText) bottleView.getChildAt(1)).getText().toString();
            String quantityString = ((EditText) bottleView.getChildAt(2)).getText().toString();
            Bottle bottle = bottles.get(i);
            bottle.setType(type);
            bottle.setPrice(priceString);
            bottle.setAvailable(quantityString);
        }

        List<Table> tables = event.getTables();
        for (int i = 0; i < llTables.getChildCount(); i++) {
            ViewGroup tableView = (ViewGroup) llTables.getChildAt(i);
            String type = ((EditText) tableView.getChildAt(0)).getText().toString();
            String priceString = ((EditText) tableView.getChildAt(1)).getText().toString();
            String quantityString = ((EditText) tableView.getChildAt(2)).getText().toString();
            Table table = tables.get(i);
            table.setType(type);
            table.setPrice(priceString);
            table.setAvailable(quantityString);
        }
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
                    event.setLocation(place.getAddress().toString());
                    presenter.onLocationDefined(place.getLatLng());
                    bLocation.setText(event.getLocation());
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
        event.setTime(dateWithTime / 1000);
        renderFormattedDate(event.getTime());
    }

    private void renderFormattedDate(long dateWithTimeSeconds) {
        long dateWithTime = dateWithTimeSeconds * 1000;
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

    @Override
    public void navigateBack() {
        onBackPressed();
    }

    @Override
    public void saveZipCode(String response) {
        event.setZipCode(response);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bCreate:
                //TODO set fields of event
                setFields();
                if (isValid(event))
                presenter.onAddButtonClick(event);
                break;
        }
    }

    private boolean isValid(Event event) {
        if (TextUtils.isEmpty(event.getClubName())) {
            showFieldError(etClubName, getString(R.string.fieldShoudNotBeEmpty));
            return false;
        }
        if (event.getTime() <= 0) {
            showMessage(getString(R.string.chooseDateAndTime));
            return false;
        }
        if (TextUtils.isEmpty(event.getLocation())) {
            showMessage(getString(R.string.chooseLocation));
            return false;
        }
        if (TextUtils.isEmpty(event.getClubCapacity())) {
            showFieldError(etClubCapacity, getString(R.string.fieldShoudNotBeEmpty));
            return false;
        }
        if (TextUtils.isEmpty(event.getClubCapacity())) {
            showFieldError(etClubCapacity, getString(R.string.fieldShoudNotBeEmpty));
            return false;
        }
        List<Ticket> ticketPrice = event.getTicketPrice();
        if (ticketPrice.isEmpty() || TextUtils.isEmpty(ticketPrice.get(0).getPrice())) {
            showFieldError(etTicketPrice, getString(R.string.fieldShoudNotBeEmpty));
            return false;
        }
        if (TextUtils.isEmpty(event.getPartyName())) {
            showFieldError(etPartyName, getString(R.string.fieldShoudNotBeEmpty));
            return false;
        }

        List<Bottle> bottles = event.getBottles();
        for (int i = 0; i < llBottles.getChildCount(); i++) {
            ViewGroup bottleView = (ViewGroup) llBottles.getChildAt(i);
            Bottle bottle = bottles.get(i);
            EditText typeView = (EditText) bottleView.getChildAt(0);
            EditText priceView = (EditText) bottleView.getChildAt(1);
            EditText availableView = (EditText) bottleView.getChildAt(2);

            if (TextUtils.isEmpty(bottle.getType())){
                showFieldError(typeView, getString(R.string.fieldShoudNotBeEmpty));
                return false;
            }
            if (TextUtils.isEmpty(bottle.getPrice())){
                showFieldError(priceView, getString(R.string.fieldShoudNotBeEmpty));
                return false;
            }
            if (TextUtils.isEmpty(bottle.getAvailable())){
                showFieldError(availableView, getString(R.string.fieldShoudNotBeEmpty));
                return false;
            }
        }

        List<Table> tables = event.getTables();
        for (int i = 0; i < llTables.getChildCount(); i++) {
            ViewGroup tableView = (ViewGroup) llTables.getChildAt(i);
            Table table = tables.get(i);
            EditText typeView = (EditText) tableView.getChildAt(0);
            EditText priceView = (EditText) tableView.getChildAt(1);
            EditText availableView = (EditText) tableView.getChildAt(2);

            if (TextUtils.isEmpty(table.getType())){
                showFieldError(typeView, getString(R.string.fieldShoudNotBeEmpty));
                return false;
            }
            if (TextUtils.isEmpty(table.getPrice())){
                showFieldError(priceView, getString(R.string.fieldShoudNotBeEmpty));
                return false;
            }
            if (TextUtils.isEmpty(table.getAvailable())){
                showFieldError(availableView, getString(R.string.fieldShoudNotBeEmpty));
                return false;
            }
        }

        return true;
    }



    private void setFields() {
        String clubName = etClubName.getText().toString();
        String clubCapacity = etClubCapacity.getText().toString();
        String partyName = etPartyName.getText().toString();
        List<Ticket> tickets = new ArrayList<>();
        String ticketPrice = (etTicketPrice.getText().toString());
        tickets.add(new Ticket(ticketPrice));
        event.setClubName(clubName);
        event.setClubCapacity(clubCapacity);
        event.setTicketPrice(tickets);
        event.setPartyName(partyName);
        updateEventBottlesAndTables();

        Log.e("Event", event.toString());
    }


    private void showFieldError(EditText editText, String error) {
        editText.setError(error);
    }
}
