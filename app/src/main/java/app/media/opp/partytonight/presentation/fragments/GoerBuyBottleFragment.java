package app.media.opp.partytonight.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.adapters.BottlesAdapter;
import butterknife.BindView;


public class GoerBuyBottleFragment extends Fragment {
    public static final String EVENT = "event";

    @BindView(R.id.rvItems)
    RecyclerView rvItems;
    private BottlesAdapter adapter;

    public static GoerBuyBottleFragment newInstance(Event event) {

        Bundle args = new Bundle();
        args.putSerializable(EVENT, event);
        GoerBuyBottleFragment fragment = new GoerBuyBottleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }
}