package app.media.opp.partytonight.data.di;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.presentation.activities.CreateEventActivity;
import app.media.opp.partytonight.presentation.activities.LaunchScreenActivity;
import app.media.opp.partytonight.presentation.activities.PromoterSignInActivity;
import app.media.opp.partytonight.presentation.activities.PromoterSignUpActivity;
import dagger.Subcomponent;

/**
 * Created by sebastian on 13.08.16.
 */
@UserScope
@Subcomponent(
        modules = {
                AccountModule.class
        }
)
public interface UserComponent {

        void inject(PromoterSignInActivity promoterSignInActivity);

        void inject(PromoterSignUpActivity promoterSignUpActivity);

        void inject(LaunchScreenActivity launchScreenActivity);

        void inject(CreateEventActivity createEventActivity);
}
