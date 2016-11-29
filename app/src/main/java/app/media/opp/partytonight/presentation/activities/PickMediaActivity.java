package app.media.opp.partytonight.presentation.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
    private final int PERMISSION_READ_EXTERNAL_STORAGE = 0;
    private final int PERMISSION_WRITE_EXTERNAL_STORAGE = 1;
    private final int PERMISSION_CAMERA = 2;
    @BindView(R.id.rvMedia)
    RecyclerView rvMedia;
    PickMediaAdapter mediaAdapter;
    boolean canPickCamera = false;
    boolean canPickGallery = false;
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
        if (canPickCamera) {
            mediaAdapter.forbidRemoving();
            cameraImagePicker.pickImage();
        } else {
            checkPermission(true);
        }
    }

    @OnClick(R.id.btnPickGallery)
    public void onClickPickGallery() {
        if (canPickGallery) {
            mediaAdapter.forbidRemoving();
            imagePicker.pickImage();
        } else {
            checkPermission(false);
        }
    }

    public void checkPermission(boolean toCamera) {
        if (toCamera) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {

                    // TODO: 11/30/16 need to add another asking to grant permissions

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSION_CAMERA);
                }
            }
        } else {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    // TODO: 11/30/16 need to add another asking to grant permissions

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            PERMISSION_READ_EXTERNAL_STORAGE);
                }
            }

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    // TODO: 11/30/16 need to add another asking to grant permissions

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_WRITE_EXTERNAL_STORAGE);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_EXTERNAL_STORAGE:
            case PERMISSION_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    canPickGallery = true;
                } else {

                }
                break;
            case PERMISSION_CAMERA:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    canPickCamera = true;
                } else {

                }
                break;
            default:
                break;
        }
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
        mediaAdapter.removeThumbnails();

        Intent returnIntent = new Intent();

        returnIntent.putExtra(MEDIA_KEY, data);

        setResult(RESULT_OK, returnIntent);

        super.finish();
    }
}
