package app.media.opp.partytonight.data.di;

import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.presentation.activities.EventScreenActivity;
import app.media.opp.partytonight.presentation.activities.GoerCartActivity;
import app.media.opp.partytonight.presentation.activities.GoerFindVenueActivity;
import app.media.opp.partytonight.presentation.activities.GoerSignInActivity;
import app.media.opp.partytonight.presentation.activities.GoerSignUpActivity;
import app.media.opp.partytonight.presentation.activities.LaunchScreenActivity;
import app.media.opp.partytonight.presentation.activities.PromoterCreateEventActivity;
import app.media.opp.partytonight.presentation.activities.PromoterEventsActivity;
import app.media.opp.partytonight.presentation.activities.PromoterSignInActivity;
import app.media.opp.partytonight.presentation.activities.PromoterSignUpActivity;
import app.media.opp.partytonight.presentation.fragments.StatementTotalFragment;
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

        void inject(PromoterCreateEventActivity promoterCreateEventActivity);

        void inject(PromoterEventsActivity promoterEventsActivity);

        void inject(EventScreenActivity eventScreenActivity);

        void inject(GoerSignInActivity goerSignInActivity);

        void inject(GoerSignUpActivity goerSignUpActivity);

        void inject(GoerFindVenueActivity goerFindVenueActivity);

        void inject(StatementTotalFragment statementTotalFragment);

        void inject(GoerCartActivity goerCartActivity);
}
