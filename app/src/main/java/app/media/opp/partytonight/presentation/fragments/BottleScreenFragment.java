package app.media.opp.partytonight.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.media.opp.partytonight.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arkadii on 11/27/16.
 */

public class BottleScreenFragment extends Fragment {
    @BindView(R.id.rvItems)
    RecyclerView rvItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_promoter_bottles_tables_details, container, false);
        ButterKnife.bind(this, view);
        rvItems.setLayoutManager(new LinearLayoutManager(getActivity()));

//        rvItems.setAdapter();
        return view;
    }
}
