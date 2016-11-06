package app.media.opp.partytonight.presentation.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.presenters.SignInPresenter;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.views.ICredentialView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PromoterSignInActivity extends ProgressActivity implements ICredentialView {

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @Inject
    SignInPresenter presenter;
    private ActivityNavigator activityNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_sign_in);
        ButterKnife.bind(this);
        PartyTonightApplication.getApp(this).getUserComponent().inject(this);
        activityNavigator = new ActivityNavigator();
        presenter.onCreate(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onRelease();
        super.onDestroy();
    }

    @OnClick({R.id.bLogIn, R.id.bSignUp})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogIn:
                presenter.onSignInButtonClick(etEmail.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.bSignUp:
                activityNavigator.startPromoterSignUpActivity(this);
                break;
        }
    }

    @Override
    public void navigateToProfile() {
        activityNavigator.startMainActivity(this);
    }

}
