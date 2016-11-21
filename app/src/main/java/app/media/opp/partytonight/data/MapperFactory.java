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
        return new Mapper<Event, EventEntity>() {
            @Override
            public EventEntity transform(Event obj) throws RuntimeException {
                EventEntity event = new EventEntity();

                event.setBottles(obj.getBottles());
                event.setClubCapacity(obj.getClubCapacity());
                event.setClubName(obj.getClubName());
                event.setLocation(obj.getLocation());
                event.setPartyName(obj.getPartyName());
                event.setTables(obj.getTables());
                event.setTime(String.valueOf(obj.getTime()));
                event.setTickets(obj.getTicketPrice());
                event.setZipCode(obj.getZipCode());

                return event;
            }
        };
    }

    @Override
    public Mapper<EventEntity, Event> getEventMapper() {
        return new Mapper<EventEntity, Event>() {
            @Override
            public Event transform(EventEntity obj) throws RuntimeException {
                Event event = new Event();

                event.setBottles(obj.getBottles());
                event.setClubCapacity(obj.getClubCapacity());
                event.setClubName(obj.getClubName());
                event.setLocation(obj.getLocation());
                event.setPartyName(obj.getPartyName());
                event.setTables(obj.getTables());
                try {
                    event.setTime(Long.parseLong(obj.getTime()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                event.setTicketPrice(obj.getTickets());
                event.setZipCode(obj.getZipCode());

                return event;
            }
        };
    }
}
