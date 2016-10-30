package app.media.opp.partytonight.presentation.presenters;

import app.media.opp.partytonight.presentation.views.ICredentialView;

/**
 * Created by arkadii on 10/30/16.
 */

public interface ISignInPresenter extends IPresenter<ICredentialView> {
    void onSignInButtonClick(String email, String password);
}
