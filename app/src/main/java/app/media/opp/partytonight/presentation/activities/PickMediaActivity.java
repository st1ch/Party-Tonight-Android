package app.media.opp.partytonight.presentation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;

import java.util.List;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.adapters.PickMediaAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/29/16
 */

public class PickMediaActivity extends Activity {

    public static String MEDIA_KEY = "media";
    @BindView(R.id.rvMedia)
    RecyclerView rvMedia;
    PickMediaAdapter mediaAdapter;
    private ImagePicker imagePicker;
    private CameraImagePicker cameraImagePicker;
    private ImagePickerCallback imagePickerCallback = new ImagePickerCallback() {
        @Override
        public void onImagesChosen(List<ChosenImage> list) {
            handlePickedImages(list);
        }

        @Override
        public void onError(String s) {

        }
    };

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

        imagePicker = new ImagePicker(PickMediaActivity.this);
        imagePicker.setImagePickerCallback(imagePickerCallback);

        cameraImagePicker = new CameraImagePicker(PickMediaActivity.this);
        cameraImagePicker.setImagePickerCallback(imagePickerCallback);

        imagePicker.allowMultiple();

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
        mediaAdapter.forbidRemoving();
        cameraImagePicker.pickImage();
    }

    @OnClick(R.id.btnPickGallery)
    public void onClickPickGallery() {
        mediaAdapter.forbidRemoving();
        imagePicker.pickImage();
    }

    @OnClick(R.id.rvRoot)
    public void onClickOutsideContainer() {
        mediaAdapter.forbidRemoving();
    }

    @OnClick(R.id.btnSubmitPick)
    public void onClickSubmitPick() {
        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Picker.PICK_IMAGE_DEVICE) {
                imagePicker.submit(data);
            } else if (requestCode == Picker.PICK_IMAGE_CAMERA) {
                cameraImagePicker.submit(data);
            }
        }
    }

    @Override
    public void finish() {
        String[] data = mediaAdapter.getItemsAsArray();

        Intent returnIntent = new Intent();

        returnIntent.putExtra(MEDIA_KEY, data);

        setResult(RESULT_OK, returnIntent);

        super.finish();
    }
}
