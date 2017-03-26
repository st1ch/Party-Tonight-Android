package app.media.opp.partytonight.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.Table;
import app.media.opp.partytonight.domain.booking.BookedTable;
import app.media.opp.partytonight.domain.booking.Booking;
import app.media.opp.partytonight.presentation.app.view.DividerThin;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 2/2/17
 */

public class GoerTablesActivity extends AppCompatActivity {

    public static final String EVENT = "event";

    private final ActivityNavigator navigator = new ActivityNavigator();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.llAllTables)
    LinearLayout llAllTables;

    private Event event;

    public static Intent launchIntent(Context context, Event event) {
        Intent intent = new Intent(context, GoerTablesActivity.class);
        intent.putExtra(EVENT, event);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goer_event_tables);
        ButterKnife.bind(this);

        event = (Event) getIntent().getSerializableExtra(EVENT);

        configureViews();
    }

    public void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);

        int amount = 0;

        for (int i = 0; i < event.getTables().size(); i++) {
            ViewGroup t_inflate = (ViewGroup) getLayoutInflater().inflate(R.layout.item_goer_tables, llAllTables);

            String t_tag = "tt" + i;

            ViewGroup t_root = (ViewGroup) t_inflate.getChildAt(i);
            t_root.setTag(t_tag);

            int amountOfTables = Integer.parseInt(event.getTables().get(i).getAvailable());

            for (int j = 0; j < amountOfTables; j++) {
                ViewGroup inflate = (ViewGroup) getLayoutInflater().inflate(R.layout.item_goer_tables_item, t_root);

                String tag = "t" + j;

                ViewGroup root = (ViewGroup) inflate.getChildAt(j);
                root.setTag(tag);

                TextView tvContent = (TextView) root.findViewById(R.id.tvContent);
                ImageView ivTick = (ImageView) root.findViewById(R.id.ivTick);
                DividerThin dividerThin = (DividerThin) root.findViewById(R.id.vDivider);

                tvContent.setText(compileContent(event.getTables().get(i), amount + 1));

                root.setOnClickListener(l -> {
                    clearTicks();

                    ivTick.setVisibility(View.VISIBLE);
                });

                amount += 1;

                if (j == amountOfTables - 1) {
                    dividerThin.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private void clearTicks() {
        for (int i = 0; i < llAllTables.getChildCount(); i++) {
            ViewGroup root = (ViewGroup) llAllTables.getChildAt(i);

            for (int j = 0; j < root.getChildCount(); j++) {
                root.getChildAt(j).findViewById(R.id.ivTick).setVisibility(View.GONE);
            }
        }
    }

    @OnClick(R.id.btnAddToCart)
    public void onClick() {
        for (int i = 0; i < llAllTables.getChildCount(); i++) {
            ViewGroup root = (ViewGroup) llAllTables.getChildAt(i);

            for (int j = 0; j < root.getChildCount(); j++) {
                int visibility = root.getChildAt(j).findViewById(R.id.ivTick).getVisibility();

                if (visibility == View.VISIBLE) {
                    BookedTable table = new BookedTable();

                    table.setPrice(Double.parseDouble(event.getTables().get(j).getPrice()));
                    table.setNumber(i * llAllTables.getChildCount() + (j + 1));
                    table.setType(event.getTables().get(j).getType());

                    GoerCartActivity.putToCart(event.getIdEvent(), new Booking(table));

                    finish();
                }
            }
        }
    }

    String compileContent(Table table, int position) {
        return "Table #" + position + "(" +
                table.getType() + " - $" + table.getPrice() + ")";
    }
}
