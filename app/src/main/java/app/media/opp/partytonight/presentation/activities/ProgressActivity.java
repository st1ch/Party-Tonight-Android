package app.media.opp.partytonight.presentation.activities;

import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.views.IProgressView;

/**
 * Created by arkadii on 10/30/16.
 */

public class ProgressActivity extends AppCompatActivity implements IProgressView {
    private AlertDialog dialog;

    @Override
    public void showProgress() {
        Log.e("ProgressActivity", "showProgress");
        if (dialog == null)
            dialog = new AlertDialog.Builder(this)
                    .setView(getLayoutInflater().inflate(R.layout.dialog_progress, null))
                    .setCancelable(false)
                    .create();

        dialog.show();

        ((ProgressBar) dialog.findViewById(R.id.progressBar)).getIndeterminateDrawable()
                .setColorFilter(getResources().getColor(R.color.colorAnimationGradientFrame_3), PorterDuff.Mode.MULTIPLY);
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
