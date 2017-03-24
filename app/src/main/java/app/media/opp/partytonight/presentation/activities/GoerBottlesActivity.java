package app.media.opp.partytonight.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Bottle;
import app.media.opp.partytonight.domain.CartItemExtended;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 2/2/17
 */

public class GoerBottlesActivity extends AppCompatActivity {

    public static final String EVENT = "event";

    @BindView(R.id.llBottles)
    LinearLayout llBottles;
    int currentAmount = 0;
    private Event event;

    public static Intent launchIntent(Context context, Event event) {
        Intent intent = new Intent(context, GoerBottlesActivity.class);
        intent.putExtra(EVENT, event);
        return intent;
    }

    private void restoreEventState(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addMoreType();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goer_event_bottles);

        ButterKnife.bind(this);

        event = (Event) getIntent().getSerializableExtra(EVENT);

        restoreEventState(savedInstanceState);

        ToolbarUtils.configureToolbarAsActionBar(this,
                (Toolbar) findViewById(R.id.toolbar), true, false);
    }

    @OnClick(R.id.btnAddMoreType)
    public void addMoreType() {
        addType(currentAmount);
    }


    private ViewGroup addType(int number) {
        ViewGroup inflate = (ViewGroup) getLayoutInflater().inflate(R.layout.item_goer_bottles_type, llBottles);

        String tag = "t" + number;

        ViewGroup viewGroup = (ViewGroup) inflate.getChildAt(number);
        viewGroup.setTag(tag);

        currentAmount += 1;

        configureViews(viewGroup);

        return viewGroup;
    }

    private void configureViews(View root) {
        Spinner spnTypes = (Spinner) root.findViewById(R.id.spnTypes);
        TextView tvBottlePrice = (TextView) root.findViewById(R.id.tvBottlePrice);
        Button btnQuantity = (Button) root.findViewById(R.id.btnQuantity);

        CharSequence[] types = new CharSequence[event.getBottles().size()];

        for (int i = 0; i < types.length; i++) {
            types[i] = event.getBottles().get(i).getType();
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.item_goer_bottle_type, types);


        spnTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String price = "$" + event.getBottles().get(i).getPrice();

                tvBottlePrice.setText(price);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnTypes.setAdapter(adapter);

        btnQuantity.setOnClickListener(l -> btnQuantity.setText(Integer.parseInt(btnQuantity.getText().toString()) + 1 + ""));
    }

    @OnClick(R.id.btnAddToCart)
    public void addToCart() {
        for (int i = 0; i < llBottles.getChildCount(); i++) {
            ViewGroup root = (ViewGroup) llBottles.getChildAt(i);

            Spinner spnTypes = (Spinner) root.findViewById(R.id.spnTypes);
            Button btnQuantity = (Button) root.findViewById(R.id.btnQuantity);

            Bottle b = event.getBottles().get(spnTypes.getSelectedItemPosition());

            int amount = Integer.parseInt(btnQuantity.getText().toString());

            if (amount == 0)
                continue;

            double price = Double.parseDouble(b.getPrice());

            GoerCartActivity.putToCart(event.getPartyName(), CartItemExtended.Type.Bottle, b.getType(), amount * price, amount);
        }

        finish();
    }

}
