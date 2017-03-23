package app.media.opp.partytonight.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Booking;
import app.media.opp.partytonight.domain.CartItemExtended;
import app.media.opp.partytonight.presentation.adapters.GoerCartAdapter;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoerCartActivity extends AppCompatActivity {

    private static List<CartItemExtended> cart = new ArrayList<>();
    private final ActivityNavigator navigator = new ActivityNavigator();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvCartItems)
    RecyclerView rvCart;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    private GoerCartAdapter adapter;

    public static void putToCart(String partyName, CartItemExtended.Type type, String title, int fullPrice, int amount) {
        CartItemExtended ci = new CartItemExtended();

        ci.setPartyName(partyName);
        ci.setFullPrice(fullPrice);
        ci.setAmount(amount);
        ci.setTypeOfItem(type);
        ci.setTitle(title);

        cart.add(ci);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goer_cart);
        ButterKnife.bind(this);

        configureViews();
    }

    private void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);

        rvCart.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GoerCartAdapter(cart);
        rvCart.setAdapter(adapter);

        String total = "Total: $" + adapter.getTotal();

        tvTotal.setText(total);
    }

    private List<Booking> compileOrder(List<CartItemExtended> cart) {
        HashMap<String, Booking> order = new HashMap<>();

        for (CartItemExtended item : cart) {
            if (order.containsKey(item.getPartyName())) {
                Booking booking = order.get(item.getPartyName());

                if (item.getTypeOfItem().equals(CartItemExtended.Type.Bottle)) {
                    for (int i = 0; i < booking.getBottles().size(); i++) {
                        if (booking.getBottles().get(i).getType().equals(item.getTitle())) {
                            String bookedAsStored = booking.getBottles().get(i).getBooked();

                            int booked = Integer.parseInt(bookedAsStored);

                            booked += Integer.parseInt(item.getBooked());

                            booking.getBottles().get(i).setBooked(String.valueOf(booked));

                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < booking.getTables().size(); i++) {
                        if (booking.getTables().get(i).getType().equals(item.getTitle())) {
                            String bookedAsStored = booking.getTables().get(i).getBooked();

                            int booked = Integer.parseInt(bookedAsStored);

                            booked += Integer.parseInt(item.getBooked());

                            booking.getTables().get(i).setBooked(String.valueOf(booked));

                            break;
                        }
                    }
                }

                order.put(booking.getPartyName(), booking);
            } else {

            }
        }

        return (List<Booking>) order.values();
    }

    @OnClick(R.id.btnPay)
    public void onClickPay() {

        // compile the order
        List<Booking> order = compileOrder(adapter.getData());

        // send order to the server.


        // receive transactions and take them to PayPal
    }

//    @OnClick(R.id.btnCart)
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnCart:
//                navigator.startGoerCartActivity(this);
//                break;
//        }
//    }
}
