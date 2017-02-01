package app.media.opp.partytonight.data;

import java.util.ArrayList;
import java.util.List;

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
        return obj -> {
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
            List<PhotoEntity> remotePhotos = new ArrayList<>();
            for (String s : obj.getPhotos()) {
                remotePhotos.add(new PhotoEntity(s));
            }
            event.setPhotos(remotePhotos);
            return event;
        };
    }

    @Override
    public Mapper<StatementEntity, Statement> getStatementMapper() {
        return obj -> {
            Statement statement = new Statement();

            statement.setBottleSales(obj.getBottleSales());
            statement.setRefunds(obj.getRefunds());
            statement.setTableSales(obj.getTableSales());
            statement.setTicketsSales(obj.getTicketsSales());
            statement.setWithdrawn(obj.getWithdrawn());

            statement.total();

            return statement;
        };
    }

    @Override
    public Mapper<EventEntity, Event> getEventMapper() {
        return obj -> {
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
        };
    }
}
