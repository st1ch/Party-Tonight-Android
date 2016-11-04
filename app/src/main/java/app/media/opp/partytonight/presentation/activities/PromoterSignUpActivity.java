package app.media.opp.partytonight.presentation.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.presenters.SignUpPresenter;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.AnimationDrawableUtil;
import app.media.opp.partytonight.presentation.views.ICredentialView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PromoterSignUpActivity extends ProgressActivity implements ICredentialView {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.etBillingInfo)
    EditText etBillingInfo;
    @BindView(R.id.etEmergencyContact)
    EditText etEmergencyContact;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @Inject
    SignUpPresenter presenter;
    AnimationDrawable animationDrawable;
    private ActivityNavigator activityNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_sign_up);
        ButterKnife.bind(this);
        PartyTonightApplication.getApp(this).getUserComponent().inject(this);
        activityNavigator = new ActivityNavigator();
        presenter.onCreate(this);

        configureAnimation(R.id.activity_promoter_sign_up);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        etName.setText("name");
//        etEmail.setText("email.com");
//        etPhoneNumber.setText("123321");
//        etPassword.setText("asdasd");
//        etBillingInfo.setText("aaa");
//        etEmergencyContact.setText("aaa");
//    }

    @Override
    protected void onDestroy() {
        presenter.onRelease();
        super.onDestroy();
    }

    @OnClick(R.id.bSignUp)
    public void onClick() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();
        String billingInfo = etBillingInfo.getText().toString();
        String emergencyContact = etEmergencyContact.getText().toString();
        presenter.onSignUpButtonClick(name, email, phone, password, billingInfo, emergencyContact);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AnimationDrawableUtil.startGradientAnimation(animationDrawable);
    }

    @Override
    protected void onStop() {
        super.onStop();

        AnimationDrawableUtil.stopGradientAnimation(animationDrawable);
    }

    @Override
    public void navigateToProfile() {
        activityNavigator.startMainActivity(this, true);
    }

    private void configureAnimation(int rootViewId) {
        final String extraTag = "AnimationFrame";

        animationDrawable = AnimationDrawableUtil.configureAnimation((ViewGroup) findViewById(rootViewId),
                6000, 2000);

        int frame = getIntent().getIntExtra(extraTag, 0);

        AnimationDrawableUtil.setAnimationFrame(animationDrawable, frame);
    }
}
