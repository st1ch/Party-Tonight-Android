package app.media.opp.partytonight.data.di;

import android.content.Context;

import java.util.List;
import java.util.Map;

import javax.inject.Named;

import app.media.opp.partytonight.data.AbstractMapperFactory;
import app.media.opp.partytonight.data.MapperFactory;
import app.media.opp.partytonight.data.PartyTonightAccount;
import app.media.opp.partytonight.data.di.scope.UserScope;
import app.media.opp.partytonight.data.repositories.SessionDataRepository;
import app.media.opp.partytonight.data.rest.PartyTonightApi;
import app.media.opp.partytonight.data.rest.RestApi;
import app.media.opp.partytonight.domain.Account;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.presentation.utils.MapUtils;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by sebastian on 13.08.16.
 */

@Module
public class AccountModule {

    @Provides
    @UserScope
    public Account provideAccount(Context context) {
        return new PartyTonightAccount(context);
    }

    @Provides
    @UserScope
    public AbstractMapperFactory provideMapperFactory() {
        return new MapperFactory();
    }

    @Provides
    @UserScope
    public RestApi provideRestApi(Context context, Retrofit retrofit, Account account) {
        return new RestApi(context, retrofit.create(PartyTonightApi.class), account);
    }

    @Provides
    @UserScope
    public SessionRepository provideRepository(RestApi restApi, MapUtils mapUtils,
                                               Account account,
                                               AbstractMapperFactory abstractMapperFactory) {
        return new SessionDataRepository(restApi, account, abstractMapperFactory, mapUtils);
    }

}
