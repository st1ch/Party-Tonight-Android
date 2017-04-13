package app.media.opp.partytonight.presentation.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import app.media.opp.partytonight.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

public class SignUpVerificationFragment extends BlurDialogFragment {

    @BindView(R.id.bProceed)
    Button bProceed;

    public static SignUpVerificationFragment newInstance() {
        return new SignUpVerificationFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View root = getActivity().getLayoutInflater().inflate(R.layout.dialog_sign_up_verification, null);

        ButterKnife.bind(this, root);

        builder.setView(root);
        return builder.create();
    }

    @OnClick({R.id.bProceed})
    public void proceed(View v) {
        switch (v.getId()) {
            case R.id.bProceed:
                getActivity().finish();
                break;
        }
    }
}
