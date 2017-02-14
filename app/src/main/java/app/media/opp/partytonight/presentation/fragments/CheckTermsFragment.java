package app.media.opp.partytonight.presentation.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.activities.GoerSignInActivity;
import app.media.opp.partytonight.presentation.activities.PromoterSignInActivity;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment;

public class CheckTermsFragment extends BlurDialogFragment {

    @BindView(R.id.cbAgreedWithTerms)
    CheckBox agreedWithTerms;

    private String email;
    private String password;

    private ActivityNavigator activityNavigator;

    public static CheckTermsFragment newInstance() {
        return new CheckTermsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityNavigator = new ActivityNavigator();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            email = bundle.getString("email");
            password = bundle.getString("password");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View root = getActivity().getLayoutInflater().inflate(R.layout.dialog_check_terms, null);

        ButterKnife.bind(this, root);

        builder.setView(root);
        return builder.create();
    }

    @OnClick({R.id.bTerms, R.id.bLogIn})
    public void goToTerms(View v) {
        switch (v.getId()) {
            case R.id.bTerms:
                activityNavigator.startTermsAndConditions(getActivity().getApplicationContext());
                break;
            case R.id.bLogIn:
                if (agreedWithTerms.isChecked()) {

                    Activity activity = getActivity();
                    if (activity instanceof GoerSignInActivity) {
                        GoerSignInActivity parent = (GoerSignInActivity) activity;

                        parent.getPresenter().onSignInButtonClick(email, password);
                    } else if (activity instanceof PromoterSignInActivity) {
                        PromoterSignInActivity parent = (PromoterSignInActivity) activity;

                        parent.getPresenter().onSignInButtonClick(email, password);
                    }
                }
                break;
        }
    }
}
