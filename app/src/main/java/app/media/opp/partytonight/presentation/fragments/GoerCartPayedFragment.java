package app.media.opp.partytonight.presentation.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import app.media.opp.partytonight.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

public class GoerCartPayedFragment extends BlurDialogFragment {

    public static GoerCartPayedFragment newInstance() {
        return new GoerCartPayedFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View root = getActivity().getLayoutInflater().inflate(R.layout.dialog_goer_cart_payed, null);

        ButterKnife.bind(this, root);

        builder.setView(root);
        return builder.create();
    }

    @OnClick({R.id.bProceed})
    public void proceed(View v) {
        finish();
        onDismiss(getDialog());
    }

    private void finish() {
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }
}