package app.media.opp.partytonight.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Table;
import app.media.opp.partytonight.presentation.app.view.EventDetailsItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arkadii on 11/27/16.
 */

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.ViewHolder> {

    private List<Table> tables = new ArrayList<>();


    public void setTables(List<Table> tables) {
        this.tables.clear();
        this.tables.addAll(tables);
        notifyItemRangeInserted(0, tables.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Table table = tables.get(position);
        holder.containerAvailable.setAdditionalLabel(String.valueOf(table.getAvailable()));
        holder.containerPurchased.setAdditionalLabel(String.valueOf(table.getBooked()));
        holder.tvLabel.setText(String.format("%s (Price: $%s)", table.getType(), table.getPrice()));
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvLabel)
        TextView tvLabel;
        @BindView(R.id.containerAvailable)
        EventDetailsItem containerAvailable;
        @BindView(R.id.containerPurchased)
        EventDetailsItem containerPurchased;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
