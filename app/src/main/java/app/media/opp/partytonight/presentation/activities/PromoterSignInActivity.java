package app.media.opp.partytonight.presentation.activities;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import app.media.opp.partytonight.R;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.presenters.SignInPresenter;
import app.media.opp.partytonight.presentation.utils.ActivityNavigator;
import app.media.opp.partytonight.presentation.utils.AnimationDrawableUtil;
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
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_sign_in);
        ButterKnife.bind(this);
        PartyTonightApplication.getApp(this).getUserComponent().inject(this);
        activityNavigator = new ActivityNavigator();
        presenter.onCreate(this);

        configureAnimation(R.id.activity_promoter_sign_in);
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
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                presenter.onSignInButtonClick(email, password);
                break;
            case R.id.bSignUp:
                activityNavigator.startPromoterSignUpActivity(this, AnimationDrawableUtil.getCurrentFrame(animationDrawable));
                break;
        }
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
