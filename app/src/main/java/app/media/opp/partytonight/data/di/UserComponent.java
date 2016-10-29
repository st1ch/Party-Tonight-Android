package app.media.opp.partytonight.data.di;

import app.media.opp.partytonight.data.di.scope.UserScope;
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

}
