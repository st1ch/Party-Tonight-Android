package app.media.opp.partytonight.data.repositories;


import app.media.opp.partytonight.data.AbstractMapperFactory;
import app.media.opp.partytonight.data.rest.RestApi;
import app.media.opp.partytonight.domain.Account;
import app.media.opp.partytonight.domain.SessionRepository;
import app.media.opp.partytonight.domain.User;
import rx.Observable;

/**
 * Created by Arkadiy on 05.06.2016.
 */
public class SessionDataRepository implements SessionRepository {

    private final AbstractMapperFactory abstractMapperFactory;
    private final Account account;
    private RestApi restApi;

    public SessionDataRepository(RestApi restApi, Account account, AbstractMapperFactory abstractMapperFactory) {
        this.restApi = restApi;
        this.abstractMapperFactory = abstractMapperFactory;
        this.account = account;
    }

    @Override
    public Observable<User> logIn(String email, String password) {
        return restApi.logIn(email, password);
    }

    @Override
    public Observable<User> signUp(String name, String email, String phone, String password) {
        return restApi.signUp(name, email, phone, password);
    }
}
