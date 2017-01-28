package app.media.opp.partytonight.presentation.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.utils.ResourcesUtils;
import app.media.opp.partytonight.presentation.views.IProgressView;

/**
 * Created by arkadii on 12/4/16.
 */

public class ProgressFragment extends Fragment implements IProgressView {
    private AlertDialog dialog;
    private int color;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        color = ResourcesUtils.getColor(getActivity(), R.color.colorAnimationGradientFrame_3);

    }

    @Override
    public void showProgress() {
        Log.e("ProgressActivity", "showProgress");
        if (dialog == null)
            dialog = new AlertDialog.Builder(getActivity())
                    .setView(getActivity().getLayoutInflater().inflate(R.layout.dialog_progress, null))
                    .setCancelable(false)
                    .create();

        dialog.show();
        ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
        if (progressBar != null) {
            progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        }
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
    public void onDestroyView() {
        hideProgress();
        super.onDestroyView();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
