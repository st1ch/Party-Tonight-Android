package app.media.opp.partytonight.data.repositories;


import app.media.opp.partytonight.data.AbstractMapperFactory;
import app.media.opp.partytonight.data.rest.RestApi;
import app.media.opp.partytonight.domain.Account;
import app.media.opp.partytonight.domain.SessionRepository;

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
}
