package app.media.opp.partytonight.presentation.presenters;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.User;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.SignUpUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.ICredentialView;

/**
 * Created by arkadii on 10/29/16.
 */

@UserScope
public class SignUpPresenter extends ProgressPresenter<ICredentialView> implements ISignUpPresenter {
    private SignUpUseCase useCase;

    @Inject
    public SignUpPresenter(Messages messages, SignUpUseCase useCase) {
        super(messages);
        this.useCase = useCase;
    }

    @Override
    public void onSignUpButtonClick(String name, String email, String phone, String password) {
        useCase.setCredentials(name, email, phone, password);
        useCase.execute(new BaseProgressSubscriber<User>(this) {
            @Override
            public void onNext(User response) {
                super.onNext(response);
                ICredentialView view = getView();
                if (view != null) {
                    view.navigateToProfile();
                }
            }
        });
    }

}
