package app.media.opp.partytonight.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.media.opp.partytonight.domain.Bottle;
import app.media.opp.partytonight.domain.Table;

/**
 * Created by arkadii on 11/6/16.
 */

public class EventEntity {
    @SerializedName("id_event")
    private int idEvent;
    @SerializedName("club_name")
    private String clubName;
    @SerializedName("party_name")
    private String partyName;
    @SerializedName("date")
    private String time;
    private String location;
    @SerializedName("zip_code")
    private String zipCode;
    @SerializedName("club_capacity")
    private String clubCapacity;
    private List<Bottle> bottles;
    private List<Table> tables;
    private List<Ticket> tickets;
    private List<PhotoEntity> photos;

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public List<PhotoEntity> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoEntity> photos) {
        this.photos = photos;
    }

    public List<Bottle> getBottles() {
        return bottles;
    }

    public void setBottles(List<Bottle> bottles) {
        this.bottles = bottles;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClubCapacity() {
        return clubCapacity;
    }

    public void setClubCapacity(String clubCapacity) {
        this.clubCapacity = clubCapacity;
    }
}
