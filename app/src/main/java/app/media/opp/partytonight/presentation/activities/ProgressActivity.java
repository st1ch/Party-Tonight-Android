package app.media.opp.partytonight.presentation.activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ResourcesUtils;
import app.media.opp.partytonight.presentation.views.IProgressView;

/**
 * Created by arkadii on 10/30/16.
 */

public class ProgressActivity extends AppCompatActivity implements IProgressView {
    private AlertDialog dialog;
    private int color;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        color = ResourcesUtils.getColor(this, R.color.colorAnimationGradientFrame_3);
    }

    @Override
    public void showProgress() {
        Log.e("ProgressActivity", "showProgress");
        if (dialog == null)
            dialog = new AlertDialog.Builder(this)
                    .setView(getLayoutInflater().inflate(R.layout.dialog_progress, null))
                    .setCancelable(false)
                    .create();

        dialog.show();
        ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
        if (progressBar != null) {
            progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        }
    }

    @Override
    protected void onDestroy() {
        hideProgress();
        super.onDestroy();
    }

    @Override
    public void hideProgress() {
        Log.e("ProgressActivity", "hideProgress");
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
