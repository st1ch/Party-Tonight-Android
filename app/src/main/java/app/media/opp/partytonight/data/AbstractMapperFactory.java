package app.media.opp.partytonight.data;


import app.media.opp.partytonight.domain.User;

/**
 * Created by sebastian on 10.06.16.
 */
public interface AbstractMapperFactory {
    Mapper<User, UserEntity> getUserEntityMapper();


}
