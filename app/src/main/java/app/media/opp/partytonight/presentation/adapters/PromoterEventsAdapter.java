package app.media.opp.partytonight.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.BitmapUtils;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/3/16
 */

public class PromoterEventsAdapter extends RecyclerView.Adapter<PromoterEventsAdapter.ViewHolder> {

    private String[] mDataset;
    private Context mContext;

    public PromoterEventsAdapter(String[] myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }

    @Override
    public PromoterEventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promoter_event, parent, false);

        return new ViewHolder((ViewGroup) v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mDataset[position]);
        holder.tvTime.setText("10:00 PM 25.07.16");
        holder.ivThumbnail.setImageBitmap(
                BitmapUtils.decodeSampledBitmapFromResource(mContext.getResources(),
                        R.drawable.image_background, 200, 100
                ));
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTime;
        private TextView tvTitle;
        private RoundedImageView ivThumbnail;

        ViewHolder(ViewGroup v) {
            super(v);

            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            tvTime = (TextView) v.findViewById(R.id.tvTime);
            ivThumbnail = (RoundedImageView) v.findViewById(R.id.ivThumbnail);
        }
    }
}
