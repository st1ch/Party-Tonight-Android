package app.media.opp.partytonight.presentation.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.MEP.PayPal;
import com.paypal.android.MEP.PayPalActivity;
import com.paypal.android.MEP.PayPalAdvancedPayment;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.booking.BookedBottle;
import app.media.opp.partytonight.domain.booking.Booking;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.adapters.GoerCartAdapter;
import app.media.opp.partytonight.presentation.fragments.GoerCartAskingPermission;
import app.media.opp.partytonight.presentation.fragments.GoerCartPayedFragment;
import app.media.opp.partytonight.presentation.fragments.GoerCartPrepareFragment;
import app.media.opp.partytonight.presentation.presenters.GoerCartPresenter;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import app.media.opp.partytonight.presentation.views.IGoerCartView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoerCartActivity extends ProgressActivity implements IGoerCartView {

    private static HashMap<Integer, Booking> cart = new HashMap<>();
    private final int PERMISSION_READ_PHONE_STATE = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvCartItems)
    RecyclerView rvCart;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @Inject
    GoerCartPresenter presenter;
    int ACTIVITY_CODE_HANDLE_PAYMENT = 1;
    private GoerCartAdapter adapter;

    public static void putToCart(int idEvent, Booking booking) {
        if (cart.containsKey(idEvent)) {
            Booking stored = cart.get(idEvent);

            // bottles
            for (BookedBottle bottle : booking.getBottlesAsMap().values()) {
                if (stored.getBottlesAsMap().containsKey(bottle.getTitle())) {
                    int storedAmount = stored.getBottlesAsMap().get(bottle.getTitle()).getAmount();
                    int bookedAmount = bottle.getAmount();

                    stored.getBottlesAsMap().get(bottle.getTitle()).setAmount(bookedAmount + storedAmount);
                } else {
                    stored.getBottlesAsMap().put(bottle.getTitle(), bottle);
                }
            }

            // single table
            if (booking.getTable() != null) {
                stored.setTable(booking.getTable());
            }

            // single ticket
            if (booking.getTicket() != null) {
                stored.setTicket(booking.getTicket());
            }

            cart.put(idEvent, stored);
        } else {
            cart.put(idEvent, booking);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goer_cart);
        ButterKnife.bind(this);
        PartyTonightApplication.getApp(this).getUserComponent().inject(this);

        configureViews();

        presenter.onCreate(this);
        presenter.validateOrder(new ArrayList<>(cart.values()));
    }

    private void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);

        rvCart.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GoerCartAdapter(cart.values());
        rvCart.setAdapter(adapter);

        actualizeTotal();
    }

    public void actualizeTotal() {
        String total = "Total: $" + adapter.getTotal();

        tvTotal.setText(total);
    }

    @OnClick(R.id.btnPay)
    public void onClickPay() {
        showPrepareMessage(() -> presenter.onOrderSent(new ArrayList<>(cart.values())));
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void handlePayment(PayPalAdvancedPayment payment) {
        Intent paymentIntent = new Intent(PayPal.getInstance().checkout(payment, this));

        startActivityForResult(paymentIntent, ACTIVITY_CODE_HANDLE_PAYMENT);
    }

    @Override
    public void handleValidatedCart(HashMap<Integer, Booking> cart) {
        if (GoerCartActivity.cart.size() != cart.size()) {
            Toast.makeText(this, "Your cart was validated", Toast.LENGTH_SHORT).show();
        }
        GoerCartActivity.cart = cart;

        adapter.setData(GoerCartActivity.cart.values());
        adapter.notifyDataSetChanged();

        actualizeTotal();
    }

    @Override
    public void askForPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_PHONE_STATE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_PHONE_STATE);
            }
        } else {
            PartyTonightApplication.buildPayPalClient(this);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_CODE_HANDLE_PAYMENT) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    String payKey = data.getStringExtra(PayPalActivity.EXTRA_PAY_KEY);
                    Log.i("PAYMENT", "OK " + payKey);

                    presenter.sendConfirmation();

                    showOkayMessage();

                    cart.clear();
                    adapter.getData().clear();
                    adapter.notifyDataSetChanged();

                    actualizeTotal();

                    break;
                case Activity.RESULT_CANCELED:
                    Log.i("PAYMENT", "CANCELED");
                    break;
                case PayPalActivity.RESULT_FAILURE:
                    String errorID = data.getStringExtra(PayPalActivity.EXTRA_ERROR_ID);
                    String errorMessage = data.getStringExtra(PayPalActivity.EXTRA_ERROR_MESSAGE);

                    Log.i("PAYMENT", "ERROR " + errorMessage);
                    break;
            }
        }
    }

    @Override
    public void showAskingPermissions(GoerCartAskingPermission.IGoerCartPrepared callback) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                GoerCartAskingPermission fragment = GoerCartAskingPermission.newInstance();
                fragment.setCallback(callback);

                fragment.show(getFragmentManager(), "goer_cart_prepare");
            } else {
                PartyTonightApplication.buildPayPalClient(this);
            }
        }
    }

    private void showPrepareMessage(GoerCartPrepareFragment.IGoerCartPrepared callback) {
        GoerCartPrepareFragment fragment = GoerCartPrepareFragment.newInstance();
        fragment.setCallback(callback);

        fragment.show(getFragmentManager(), "goer_cart_prepare");
    }

    private void showOkayMessage() {
        GoerCartPayedFragment fragment = GoerCartPayedFragment.newInstance();

        fragment.show(getFragmentManager(), "goer_cart_payed");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_PHONE_STATE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    PartyTonightApplication.buildPayPalClient(this);
                } else {
                    finish();
                }
            }
        }
    }
}
