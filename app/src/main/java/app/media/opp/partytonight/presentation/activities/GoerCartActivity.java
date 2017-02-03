package app.media.opp.partytonight.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.CartItem;
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

    public static void putToCart(CartItem item, CartItemExtended.Type type, String title, int fullPrice, int amount) {
        CartItemExtended ci = new CartItemExtended();

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

    @OnClick(R.id.btnCart)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCart:
                navigator.startGoerCartActivity(this);
                break;
        }
    }
}
