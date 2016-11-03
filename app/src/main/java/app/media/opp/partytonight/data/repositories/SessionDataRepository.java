package app.media.opp.partytonight.data.repositories;


import app.media.opp.partytonight.data.AbstractMapperFactory;
import app.media.opp.partytonight.data.Mapper;
import app.media.opp.partytonight.data.TokenEntity;
import app.media.opp.partytonight.data.UserEntity;
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
    public Observable<User> signUp(User user) {
        Mapper<User, UserEntity> userEntityMapper = abstractMapperFactory.getUserEntityMapper();
        return restApi.signUp(userEntityMapper.transform(user)).map(tokenEntity -> saveUser(user, tokenEntity));
    }

    @Override
    public Observable<User> logIn(User user) {
        Mapper<User, UserEntity> userEntityMapper = abstractMapperFactory.getUserEntityMapper();
        return restApi.logIn(userEntityMapper.transform(user)).map(tokenEntity -> saveUser(user, tokenEntity));
    }

    private User saveUser(User user, TokenEntity tokenEntity) {
        user.setToken(tokenEntity.getToken());
        account.saveUser(user);
        return user;
    }
}
