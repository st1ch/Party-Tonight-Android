package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.User;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.SignInUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.ICredentialView;

/**
 * Created by arkadii on 10/29/16.
 */

@UserScope
public class SignInPresenter extends ProgressPresenter<ICredentialView> implements ISignInPresenter {
    private SignInUseCase useCase;

    @Inject
    public SignInPresenter(Messages messages, SignInUseCase useCase) {
        super(messages);
        this.useCase = useCase;
    }

    @Override
    public void onSignInButtonClick(String email, String password) {
        useCase.setCredentials(new User(email, password));
        useCase.execute(getSubscriber());
    }

    @Override
    public void onCreate(ICredentialView view) {
        super.onCreate(view);
        if (useCase.isWorking()) {
            useCase.execute(getSubscriber());
        }
    }

    @Override
    public void onRelease() {
        useCase.unsubscribe();
        super.onRelease();
    }

    @NonNull
    private BaseProgressSubscriber<User> getSubscriber() {
        return new BaseProgressSubscriber<User>(this) {
            @Override
            public void onNext(User response) {
                super.onNext(response);
                ICredentialView view = getView();
                if (view != null) {
                    view.navigateToProfile();
                }
            }
        };
    }
}
