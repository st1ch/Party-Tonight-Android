package app.media.opp.partytonight.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.Table;
import app.media.opp.partytonight.presentation.adapters.TablesAdapter;
import app.media.opp.partytonight.presentation.app.view.EventDetailsItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arkadii on 11/27/16.
 */

public class StatementTotalFragment extends ProgressFragment {
    public static final String EVENT = "event";

    @BindView(R.id.ediStatement)
    EventDetailsItem ediStatement;
    @BindView(R.id.ediWithdraw)
    EventDetailsItem ediWithdraw;
    @BindView(R.id.ediTicketSales)
    EventDetailsItem ediTicketSales;
    @BindView(R.id.ediBottleSales)
    EventDetailsItem ediBottleSales;
    @BindView(R.id.ediTableSales)
    EventDetailsItem ediTableSales;
    @BindView(R.id.ediRefunds)
    EventDetailsItem ediRefunds;

    private Event event;

    public static StatementTotalFragment newInstance(Event event) {

        Bundle args = new Bundle();
        args.putSerializable(EVENT, event);
        StatementTotalFragment fragment = new StatementTotalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = (Event) getArguments().getSerializable(EVENT);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_promoter_statement_total, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
