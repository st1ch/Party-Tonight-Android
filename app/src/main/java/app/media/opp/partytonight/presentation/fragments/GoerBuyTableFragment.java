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
import app.media.opp.partytonight.presentation.adapters.GoerTablesOuterAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class GoerBuyTableFragment extends Fragment {
    public static final String EVENT = "event";

    @BindView(R.id.rvTables)
    RecyclerView rvTables;

    private GoerTablesOuterAdapter adapter;

    public static GoerBuyTableFragment newInstance(Event event) {

        Bundle args = new Bundle();
        args.putSerializable(EVENT, event);
        GoerBuyTableFragment fragment = new GoerBuyTableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_goer_buy_tables, container, false);
        ButterKnife.bind(this, view);

        rvTables.setLayoutManager(new LinearLayoutManager(getActivity()));
        Event event = (Event) getArguments().getSerializable(EVENT);

        adapter = new GoerTablesOuterAdapter();
        rvTables.setAdapter(adapter);

        if (event != null) {
            List<Table> tables = event.getTables();
            adapter.setData(tables);
        }

        return view;
    }
}