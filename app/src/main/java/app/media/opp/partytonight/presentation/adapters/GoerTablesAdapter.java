package app.media.opp.partytonight.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Table;

public class GoerTablesAdapter extends RecyclerView.Adapter<GoerTablesAdapter.ViewHolder> {

    private ArrayList<Table> data;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goer_tables_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.compileContent(data.get(position), position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;
        ImageView ivTick;

        public ViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            ivTick = (ImageView) itemView.findViewById(R.id.ivTick);
        }

        void compileContent(Table table, int position) {
            tvContent.setText("Table #" + position + "(" +
                    table.getType() + " - $" + table.getPrice() + ")");
        }

        void onClick() {
            if (ivTick.getVisibility() == View.INVISIBLE
                    || ivTick.getVisibility() == View.GONE) {
                ivTick.setVisibility(View.VISIBLE);
            } else {
                ivTick.setVisibility(View.GONE);
            }
        }
    }
}
