package app.media.opp.partytonight.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.kbeanie.multipicker.api.entity.ChosenImage;

import java.util.ArrayList;
import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.FileUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/29/16
 */

public class PickMediaAdapter extends RecyclerView.Adapter<PickMediaAdapter.ViewHolder> {

    private ArrayList<ChosenImage> data;
    private RecyclerView parent;

    public PickMediaAdapter(ArrayList<ChosenImage> data) {
        this.data = data;
    }

    public PickMediaAdapter(String[] paths) {
        data = new ArrayList<>();

        for (String path : paths) {
            ChosenImage loaded = new ChosenImage();
            loaded.setOriginalPath(path);

            data.add(loaded);
        }
    }

    public PickMediaAdapter() {
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_dialog_pick_media, parent, false);

        return new PickMediaAdapter.ViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        parent = recyclerView;
    }

    public void forbidRemoving() {
        if (parent != null) {

            for (int i = 0; i < parent.getLayoutManager().getChildCount(); i++) {
                parent.getLayoutManager()
                        .getChildAt(i)
                        .findViewById(R.id.btnRemoveItem)
                        .setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChosenImage image = data.get(position);

        if (image.getThumbnailPath() != null) {
            holder.ivContent.setImageBitmap(FileUtils.getBitmapFromFile(image.getThumbnailPath()));
        } else {
            holder.ivContent.setImageBitmap(FileUtils.getBitmapFromFile(image.getOriginalPath()));
        }


        holder.ivContent.setOnLongClickListener(v -> {
            forbidRemoving();
            holder.btnRemove.setVisibility(View.VISIBLE);
            return false;
        });

        holder.btnRemove.setOnClickListener(v -> {
            removeItem(position);
            holder.btnRemove.setVisibility(View.GONE);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void removeItem(int position) {
        if (position >= data.size()) {
            data.remove(data.size() - 1);
        } else data.remove(position);

        notifyItemRemoved(position);
    }

    public void addItems(List<ChosenImage> imageList) {
        data.addAll(imageList);

        notifyDataSetChanged();
    }

    public void addItem(ChosenImage image) {
        data.add(image);

        notifyDataSetChanged();
    }

    public String[] getItemsAsArray() {
        ArrayList<String> paths = new ArrayList<>(data.size());

        for (ChosenImage image : data) {
            paths.add(image.getOriginalPath());
        }

        return paths.toArray(new String[paths.size()]);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivContent)
        ImageView ivContent;

        @BindView(R.id.btnRemoveItem)
        ImageButton btnRemove;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
