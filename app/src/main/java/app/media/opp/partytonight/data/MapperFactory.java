package app.media.opp.partytonight.data;

import app.media.opp.partytonight.domain.Event;
import app.media.opp.partytonight.domain.User;

/**
 * Created by sebastian on 10.06.16.
 */
public class MapperFactory implements AbstractMapperFactory {

    @Override
    public Mapper<User, UserEntity> getUserEntityMapper() {
        return obj -> new UserEntity(
                obj.getUserName(),
                obj.getEmail(),
                obj.getPhoneNumber(),
                obj.getBillingInfo() != null ? new BillingEntity(obj.getBillingInfo().getCardNumber()) : null,
                obj.getEmergencyContact(),
                obj.getPassword()
        );
    }

    @Override
    public Mapper<Event, EventEntity> getEventEntityMapper() {
        return null;
    }

    @Override
    public Mapper<EventEntity, Event> getEventMapper() {
        return null;
    }
}
