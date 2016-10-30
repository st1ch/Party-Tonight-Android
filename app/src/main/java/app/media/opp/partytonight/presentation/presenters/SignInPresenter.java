package app.media.opp.partytonight.presentation.presenters;

import javax.inject.Inject;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.presentation.utils.Messages;
import app.media.opp.partytonight.presentation.views.ICredentialView;

/**
 * Created by arkadii on 10/29/16.
 */

@UserScope
public class SignInPresenter extends ProgressPresenter<ICredentialView> {
    @Inject
    public SignInPresenter(Messages messages) {
        super(messages);
    }
}
