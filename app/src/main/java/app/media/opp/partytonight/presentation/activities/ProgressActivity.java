package app.media.opp.partytonight.presentation.activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
        if (dialog == null)
            dialog = new AlertDialog.Builder(this)
                    .setView(getLayoutInflater().inflate(R.layout.dialog_progress, null))
                    .setCancelable(false)
                    .create();

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        hideProgress();
        super.onDestroy();
    }

    @Override
    public void hideProgress() {
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