package app.media.opp.partytonight.presentation.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

public class CheckAgeFragment extends BlurDialogFragment {

    @BindView(R.id.cbCanBuy)
    CheckBox cbCanBuy;

    private ActivityNavigator navigator;
    private Event event;

    public static CheckAgeFragment newInstance() {
        return new CheckAgeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navigator = new ActivityNavigator();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            event = (Event) bundle.getSerializable("event");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View root = getActivity().getLayoutInflater().inflate(R.layout.dialog_can_liquor, null);

        ButterKnife.bind(this, root);

        builder.setView(root);
        return builder.create();
    }

    @OnClick({R.id.bProceed})
    public void proceed(View v) {
        switch (v.getId()) {
            case R.id.bProceed:
                if (cbCanBuy.isChecked()) {
                    navigator.startGoerBottlesActivity(getActivity(), event);
                } else {
                    finish();
                }
                break;
        }
    }

    private void finish() {
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }
}
