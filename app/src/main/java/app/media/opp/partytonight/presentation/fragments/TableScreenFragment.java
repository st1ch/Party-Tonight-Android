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
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arkadii on 11/27/16.
 */

public class TableScreenFragment extends Fragment {
    public static final String EVENT = "event";

    @BindView(R.id.rvItems)
    RecyclerView rvItems;
    private TablesAdapter adapter;

    public static TableScreenFragment newInstance(Event event) {

        Bundle args = new Bundle();
        args.putSerializable(EVENT, event);
        TableScreenFragment fragment = new TableScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_promoter_bottles_tables_details, container, false);
        ButterKnife.bind(this, view);
        rvItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        Event event = (Event) getArguments().getSerializable(EVENT);
        adapter = new TablesAdapter();
        rvItems.setAdapter(adapter);
        List<Table> tables = event.getTables();
        adapter.setTables(tables);
        return view;
    }
}
