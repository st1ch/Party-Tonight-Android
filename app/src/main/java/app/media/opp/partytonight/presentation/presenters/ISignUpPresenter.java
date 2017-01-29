package app.media.opp.partytonight.presentation.presenters;

import app.media.opp.partytonight.presentation.views.ICredentialView;

/**
 * Created by arkadii on 10/30/16.
 */

public interface ISignUpPresenter extends IPresenter<ICredentialView> {

    void onSignUpButtonClick(String name, String email, String phone, String password, String billingInfo, String emergencyContact);

    void onSignUpButtonClick(String name, String email, String password);
}
