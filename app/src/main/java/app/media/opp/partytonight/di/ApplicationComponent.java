package app.media.opp.partytonight.di;

import javax.inject.Singleton;

import app.media.opp.partytonight.data.di.AccountModule;
import app.media.opp.partytonight.data.di.UserComponent;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    UserComponent plus(AccountModule userModule);
}
