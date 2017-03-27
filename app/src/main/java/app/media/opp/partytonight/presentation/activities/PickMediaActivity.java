package app.media.opp.partytonight.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.kbeanie.multipicker.api.entity.ChosenVideo;
import com.noelchew.multipickerwrapper.library.MultiPickerWrapper;
import com.noelchew.multipickerwrapper.library.ui.MultiPickerWrapperAppCompatActivity;

import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.adapters.PickMediaAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickMediaActivity extends MultiPickerWrapperAppCompatActivity {

    public static String MEDIA_KEY = "media";

    @BindView(R.id.rvMedia)
    RecyclerView rvMedia;

    PickMediaAdapter mediaAdapter;

    MultiPickerWrapper.PickerUtilListener multiPickerWrapperListener = new MultiPickerWrapper.PickerUtilListener() {
        @Override
        public void onPermissionDenied() {

        }

        @Override
        public void onImagesChosen(List<ChosenImage> list) {
            mediaAdapter.addItems(list);
        }

        @Override
        public void onVideosChosen(List<ChosenVideo> list) {

        }

        @Override
        public void onError(String s) {

        }
    };

    @Override
    protected MultiPickerWrapper.PickerUtilListener getMultiPickerWrapperListener() {
        return multiPickerWrapperListener;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_promoter_media_picker);

        configure();
    }

    public void configure() {
        String[] loadedAlready = getIntent().getStringArrayExtra(MEDIA_KEY);
        if (loadedAlready != null) {
            mediaAdapter = new PickMediaAdapter(loadedAlready);
        }

        configureViews();
    }

    public void handlePickedImages(List<ChosenImage> images) {
        mediaAdapter.addItems(images);
    }

    public void configureViews() {
        ButterKnife.bind(this);

        if (mediaAdapter == null) {
            mediaAdapter = new PickMediaAdapter();
        }

        rvMedia.setLayoutManager(new GridLayoutManager(this, 3));
        rvMedia.setAdapter(mediaAdapter);

    }

    @OnClick(R.id.btnPickPhoto)
    public void onClickPickPhoto() {
        multiPickerWrapper.getPermissionAndTakePicture();
    }

    @OnClick(R.id.btnPickGallery)
    public void onClickPickGallery() {
        multiPickerWrapper.getPermissionAndPickMultipleImage();
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        Permiso.getInstance().onRequestPermissionResult(requestCode, permissions, grantResults);
//    }

    @OnClick(R.id.rvRoot)
    public void onClickOutsideContainer() {
        mediaAdapter.forbidRemoving();
    }

    @OnClick(R.id.btnSubmitPick)
    public void onClickSubmitPick() {
        onBackPressed();
    }

    @Override
    public void finish() {
        String[] data = mediaAdapter.getItemsAsArray();
        mediaAdapter.removeThumbnails();

        Intent returnIntent = new Intent();

        returnIntent.putExtra(MEDIA_KEY, data);

        setResult(RESULT_OK, returnIntent);

        super.finish();
    }
}
