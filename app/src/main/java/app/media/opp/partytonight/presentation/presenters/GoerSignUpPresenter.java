package app.media.opp.partytonight.presentation.presenters;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.domain.Billing;
import app.media.opp.partytonight.domain.User;
import app.media.opp.partytonight.domain.subscribers.BaseProgressSubscriber;
import app.media.opp.partytonight.domain.usecase.GoerSignUpUseCase;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.ICredentialView;

@UserScope
public class GoerSignUpPresenter extends ProgressPresenter<ICredentialView> implements ISignUpPresenter {
    private GoerSignUpUseCase useCase;

    @Inject
    public GoerSignUpPresenter(Messages messages, GoerSignUpUseCase useCase) {
        super(messages);
        this.useCase = useCase;
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

    @Override
    public void onSignUpButtonClick(String name, String email, String phone,
                                    String password, String billingInfo, String emergencyContact) {
        useCase.setUser(new User(name, email, phone, new Billing(billingInfo), emergencyContact, password));
        useCase.execute(getSubscriber());
    }

    @Override
    public void onSignUpButtonClick(String name, String email, String password) {
        useCase.setUser(new User(name, email, password));
        useCase.execute(getSubscriber());
    }

    @Override
    public void onSignUpButtonClick(String name, String email, String password, String birthday, String address) {
        useCase.setUser(new User(name, email, password, birthday, address));
        useCase.execute(getSubscriber());
    }

    @Override
    public void onSignUpButtonClick(String name, String email, String phone,
                                    String password, String billingInfo, String emergencyContact, String birthday) {
        useCase.setUser(new User(name, email, phone, new Billing(billingInfo), emergencyContact, password, birthday));
        useCase.execute(getSubscriber());
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
