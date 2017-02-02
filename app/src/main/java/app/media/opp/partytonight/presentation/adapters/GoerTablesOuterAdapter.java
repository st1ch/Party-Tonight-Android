package app.media.opp.partytonight.presentation.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Table;

public class GoerTablesOuterAdapter extends RecyclerView.Adapter<GoerTablesOuterAdapter.ViewHolder> {

    private List<Table> data;

    @Override
    public GoerTablesOuterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goer_tables, parent, false);
        return new GoerTablesOuterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoerTablesOuterAdapter.ViewHolder holder, int position) {
        holder.compileData(data.get(position));
    }

    public void setData(List<Table> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView rvItem;

        GoerTablesInnerAdapter adapter;

        public ViewHolder(View itemView) {
            super(itemView);

            rvItem = (RecyclerView) itemView.findViewById(R.id.rvItems);

            rvItem.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

            adapter = new GoerTablesInnerAdapter();

            rvItem.setAdapter(adapter);
        }

        public void compileData(Table table) {
//            ArrayList<>
        }
    }
}