package app.media.opp.partytonight.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Bottle;
import app.media.opp.partytonight.presentation.app.view.EventDetailsItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arkadii on 11/27/16.
 */

public class BottlesAdapter extends RecyclerView.Adapter<BottlesAdapter.ViewHolder> {

    private List<Bottle> bottles = new ArrayList<>();


    public void setBottles(List<Bottle> bottles) {
        this.bottles.clear();
        this.bottles.addAll(bottles);
        notifyItemRangeInserted(0, bottles.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bottle bottle = bottles.get(position);
        holder.containerAvailable.setAdditionalLabel(String.valueOf(bottle.getAvailable()));
        holder.containerPurchased.setAdditionalLabel(String.valueOf(bottle.getBooked()));
        holder.tvLabel.setText(String.format("%s (Price: $%s)", bottle.getType(), bottle.getPrice()));
    }

    @Override
    public int getItemCount() {
        return bottles.size();
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
