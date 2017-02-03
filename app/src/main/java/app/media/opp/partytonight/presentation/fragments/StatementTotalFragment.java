package app.media.opp.partytonight.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.data.Statement;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.app.view.EventDetailsItem;
import app.media.opp.partytonight.presentation.presenters.StatementPresenter;
import app.media.opp.partytonight.presentation.views.IStatementTotal;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StatementTotalFragment extends ProgressFragment implements IStatementTotal {
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
    @Inject
    StatementPresenter presenter;
    private Event event;

    public static StatementTotalFragment newInstance(Event event) {

        Bundle args = new Bundle();
        args.putSerializable(EVENT, event);
        StatementTotalFragment fragment = new StatementTotalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        presenter.onRelease();
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = (Event) getArguments().getSerializable(EVENT);
        PartyTonightApplication.getApp(this.getContext()).getUserComponent().inject(this);

        presenter.onCreate(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_promoter_statement_total, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public String validateSum(String sum) {
        String title;
        double refund = Double.parseDouble(sum);

        if (refund < 0) {
            title = "-$" + (-refund);
        } else {
            title = "$" + refund;
        }
        return title;
    }

    @Override
    public void showStatement(Statement statement) {
        if (statement != null) {
            ediStatement.setAdditionalLabel(validateSum(statement.getTotal()));
            ediBottleSales.setAdditionalLabel("$" + statement.getBottleSales());
            ediTableSales.setAdditionalLabel("$" + statement.getTableSales());
            ediTicketSales.setAdditionalLabel("$" + statement.getTicketsSales());
            ediRefunds.setAdditionalLabel(validateSum(statement.getRefunds()));
            ediWithdraw.setAdditionalLabel(statement.getWithdrawn());
        }
    }

    @Override
    public String getPartyName() {
        return event.getPartyName();
    }
}
