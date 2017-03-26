package app.media.opp.partytonight.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.Table;
import app.media.opp.partytonight.domain.booking.BookedTable;
import app.media.opp.partytonight.domain.booking.Booking;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.app.view.DividerThin;
import app.media.opp.partytonight.presentation.presenters.GoerTablesPresenter;
import app.media.opp.partytonight.presentation.utils.ToolbarUtils;
import app.media.opp.partytonight.presentation.views.IGoerTablesView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 2/2/17
 */

public class GoerTablesActivity extends ProgressActivity implements IGoerTablesView {

    public static final String EVENT = "event";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.llAllTables)
    LinearLayout llAllTables;
    @Inject
    GoerTablesPresenter presenter;
    ArrayList<BookedTable> commonList = new ArrayList<>();
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

        PartyTonightApplication.getApp(this).getUserComponent().inject(this);

        presenter.onCreate(this);
        presenter.getFreeTables(event.getIdEvent());

        configureViews();
    }

    public void configureViews() {
        ToolbarUtils.configureToolbarAsActionBar(this, toolbar, true);
    }

    public void fillTables(HashMap<String, ArrayList<BookedTable>> list) {
        int i = 0;

        commonList.clear();

        for (String key : list.keySet()) {
            ViewGroup t_inflate = (ViewGroup) getLayoutInflater().inflate(R.layout.item_goer_tables, llAllTables);

            String t_tag = "tt" + key;

            ViewGroup t_root = (ViewGroup) t_inflate.getChildAt(i);
            t_root.setTag(t_tag);

            int amountOfTables = list.get(key).size();

            for (int j = 0; j < amountOfTables; j++) {
                ViewGroup inflate = (ViewGroup) getLayoutInflater().inflate(R.layout.item_goer_tables_item, t_root);

                String tag = "t" + j;

                ViewGroup root = (ViewGroup) inflate.getChildAt(j);
                root.setTag(tag);

                TextView tvContent = (TextView) root.findViewById(R.id.tvContent);
                ImageView ivTick = (ImageView) root.findViewById(R.id.ivTick);
                DividerThin dividerThin = (DividerThin) root.findViewById(R.id.vDivider);

                tvContent.setText(compileContent(list.get(key).get(j)));
                commonList.add(list.get(key).get(j));

                root.setOnClickListener(l -> {
                    clearTicks();

                    ivTick.setVisibility(View.VISIBLE);
                });

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

                    GoerCartActivity.putToCart(event.getIdEvent(), new Booking(event.getIdEvent(), commonList.get(j)));

                    finish();
                }
            }
        }
    }

    String compileContent(Table table, int position) {
        return "Table #" + position + "(" +
                table.getType() + " - $" + table.getPrice() + ")";
    }

    String compileContent(BookedTable bookedTable) {
        return "Table #" + bookedTable.getNumber() + "(" +
                bookedTable.getType() + " - $" + bookedTable.getPrice() + ")";
    }

    @Override
    public void emptyResponse() {

    }

    @Override
    public void renderList(List<BookedTable> response) {
        HashMap<String, ArrayList<BookedTable>> compiledList = presenter.compileList(response);

        fillTables(compiledList);
    }
}
