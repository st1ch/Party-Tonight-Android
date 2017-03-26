package app.media.opp.partytonight.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.CartItemExtended;
import app.media.opp.partytonight.domain.booking.Booking;
import app.media.opp.partytonight.presentation.app.view.DividerThin;
import app.media.opp.partytonight.presentation.app.view.EventDetailsItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GoerCartAdapter extends RecyclerView.Adapter<GoerCartAdapter.ViewHolder> {

    private List<CartItemExtended> data = new ArrayList<>();

    public GoerCartAdapter(Collection<Booking> data) {
        for (Booking b : data) {
            this.data.addAll(b.toCartItems());
        }
    }

    public List<CartItemExtended> getData() {
        return data;
    }

    public void setData(Collection<Booking> data) {
        this.data.clear();

        for (Booking b : data) {
            this.data.addAll(b.toCartItems());
        }

        notifyItemRangeInserted(0, data.size());
    }

    @Override
    public GoerCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goer_cart, parent, false);
        return new GoerCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartItemExtended item = data.get(position);

        String title;

        if (item.getTypeOfItem() == CartItemExtended.Type.Bottle) {
            title = item.getTitle() + " " + item.getTypeOfItem().toString() + " ";

            if (item.getTypeOfItem().equals(CartItemExtended.Type.Bottle)) {
                title += "x" + item.getAmount();
            }
        } else {
            title = "Table #" + item.getNumber() + " (" + item.getTitle() + ")";
        }


        if (position == data.size() - 1) {
            holder.divider.setVisibility(View.GONE);
        }

        holder.content.setLabel(title);
        holder.content.setAdditionalLabel("$" + item.getFullPrice());
    }

    public int getTotal() {
        int sum = 0;
        for (CartItemExtended c : data) {
            sum += c.getFullPrice();
        }

        return sum;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvContent)
        EventDetailsItem content;

        @BindView(R.id.vDivider)
        DividerThin divider;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}