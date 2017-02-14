package app.media.opp.partytonight.presentation.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import app.media.opp.partytonight.R;
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 2/14/17
 */

public class ContactUsFragment extends BlurDialogFragment {

    public static ContactUsFragment newInstance() {
        return new ContactUsFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View root = getActivity().getLayoutInflater().inflate(R.layout.dialog_contact_us, null);

        builder.setView(root);
        return builder.create();
    }

}
