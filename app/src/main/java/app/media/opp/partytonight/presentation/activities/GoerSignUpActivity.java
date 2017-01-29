package app.media.opp.partytonight.presentation.activities;

import android.os.Bundle;
import android.widget.EditText;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.presenters.GoerSignUpPresenter;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.FieldsUtils;
import app.media.opp.partytonight.presentation.views.ICredentialView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoerSignUpActivity extends ProgressActivity implements ICredentialView {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;

    @Inject
    GoerSignUpPresenter presenter;
    private ActivityNavigator activityNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goer_sign_up);
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

    @OnClick(R.id.bSignUp)
    public void onClick() {
        String name = etName.getText().toString().trim().replace("  ", " ");
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (isValid(name, email, password)) {
            presenter.onSignUpButtonClick(name, email, password);
        }
    }

    @OnClick(R.id.bSignIn)
    public void backToSignIn() {
        onBackPressed();
    }

    private boolean isValid(String name, String email, String password) {

        boolean isValidName = false;
        if (name.isEmpty()) {
            showFieldError(etName, getString(R.string.fieldShoudNotBeEmpty));
        } else if (!FieldsUtils.isValidString(FieldsUtils.NAME_VALID_SYMBOLS, name)) {
            showFieldError(etName, getString(R.string.fieldContainsInvalidCharacters));
        } else {
            isValidName = true;
        }

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
        return isValidEmail && isValidName && isValidPassword;
    }


    private void showFieldError(EditText editText, String error) {
        editText.setError(error);
    }

    @Override
    public void navigateToProfile() {
        activityNavigator.startGoerMainActivity(this, true);
    }
}
