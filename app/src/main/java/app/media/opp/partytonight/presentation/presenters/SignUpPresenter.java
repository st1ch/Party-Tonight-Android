package app.media.opp.partytonight.presentation.presenters;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.ICredentialView;

/**
 * Created by arkadii on 10/29/16.
 */

@UserScope
public class SignUpPresenter extends ProgressPresenter<ICredentialView> implements ISignUpPresenter {
    @Inject
    public SignUpPresenter(Messages messages) {
        super(messages);
    }

    @Override
    public void onSignUpButtonClick() {

    }

}
