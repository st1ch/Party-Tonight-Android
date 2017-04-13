package app.media.opp.partytonight.presentation.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.dialogs.SignInMessageFragment;
import app.media.opp.partytonight.presentation.fragments.CheckTermsFragment;
import app.media.opp.partytonight.presentation.presenters.SignInPresenter;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.FieldsUtils;
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
                checkTerms();
                break;
            case R.id.bSignUp:
                activityNavigator.startPromoterSignUpActivity(this);
                break;
        }
    }

    private void checkTerms() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (isValid(email, password)) {
            CheckTermsFragment fragment
                    = CheckTermsFragment.newInstance();

            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            bundle.putString("password", password);
            fragment.setArguments(bundle);

            fragment.show(getFragmentManager(), "terms");
        }
    }

    private boolean isValid(String email, String password) {
        boolean isValidEmail = false;
        if (email.isEmpty()) {
            showFieldError(etEmail, getString(R.string.fieldShoudNotBeEmpty));
        } else if (!FieldsUtils.hasProperLength(email)) {
            showFieldError(etEmail, getString(R.string.minimumLengthIs) + " " + FieldsUtils.MIN_LENGTH);
        } else if (!FieldsUtils.isValidString(FieldsUtils.EMAIL_VALID_SYMBOLS, email)) {
            showFieldError(etEmail, getString(R.string.fieldContainsInvalidCharacters));
        } else if (!email.contains("@")) {
            showFieldError(etEmail, getString(R.string.fieldDoesNotContainEt));
        } else if (!email.contains(".")) {
            showFieldError(etEmail, getString(R.string.fieldDoesNotContainDot));
        } else {
            isValidEmail = true;
        }
        boolean isValidPassword = false;
        if (password.isEmpty()) {
            showFieldError(etPassword, getString(R.string.fieldShoudNotBeEmpty));
        } else if (!FieldsUtils.hasProperLength(password)) {
            showFieldError(etPassword, getString(R.string.minimumLengthIs) + " " + FieldsUtils.MIN_LENGTH);
        } else if (!FieldsUtils.isValidString(FieldsUtils.PASSWORD_VALID_SYMBOLS, password)) {
            showFieldError(etPassword, getString(R.string.passwordShouldContainOnlyLettersAndDigits));
        } else {
            isValidPassword = true;
        }

        return isValidEmail && isValidPassword;
    }

    private void showFieldError(EditText editText, String error) {
        editText.setError(error);
    }

    @Override
    public void navigateToProfile() {
        activityNavigator.startPromoterMainActivity(this, true);
    }

    @Override
    public void showMessageAboutVerification() {
        SignInMessageFragment fragment = SignInMessageFragment.newInstance();
        fragment.show(getFragmentManager(), "sign_message");
    }

    public SignInPresenter getPresenter() {
        return presenter;
    }
}
