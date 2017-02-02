package app.media.opp.partytonight.presentation.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 2/2/17
 */

public class GoerEventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private final List<Event> events = new ArrayList<>();
    private final Context context;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a dd.MM.yy");
    @Nullable
    private OnClickListener listener;

    public GoerEventsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public EventsAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goer_venue, parent, false);
        return new EventsAdapter.EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsAdapter.EventViewHolder holder, final int position) {
        final Event event = events.get(position);

        holder.tvTitle.setText(event.getPartyName());
        holder.tvTime.setText(dateFormat.format(new Date(event.getTime() * 1000)));
        List<String> photos = event.getPhotos();
        if (photos != null && !photos.isEmpty()) {
            Picasso.with(context).load(photos.get(0)).into(holder.ivThumbnail);
        }
        holder.bManage.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(position, event);
            }
        });

        holder.tvTitle.setSelected(true);
        holder.tvTitle.setHorizontallyScrolling(true);

    }

    @Override
    public void onViewRecycled(EventsAdapter.EventViewHolder holder) {
        holder.bManage.setOnClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void setData(List<Event> newData) {
        events.clear();
        events.addAll(newData);
        notifyDataSetChanged();
    }

    public void setListener(@Nullable OnClickListener listener) {
        this.listener = listener;
    }

    public void clear() {
        events.clear();
        notifyDataSetChanged();
    }

    public interface OnClickListener {
        void onClick(int position, Event event);
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivThumbnail)
        ImageView ivThumbnail;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.bManage)
        Button bManage;

        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
