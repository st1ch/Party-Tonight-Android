package app.media.opp.partytonight.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import app.media.opp.partytonight.domain.Bottle;
import app.media.opp.partytonight.domain.Table;

/**
 * Created by arkadii on 11/6/16.
 */

public class EventEntity {
    @SerializedName("club_name")
    private String clubName;
    @SerializedName("party_name")
    private String partyName;
    private long time;
    private String location;
    @SerializedName("zip_code")
    private String zipCode;
    @SerializedName("club_capacity")
    private int clubCapacity;
    private List<Bottle> bottles;
    private List<Table> tables;
    private List<Ticket> tickets;


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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getClubCapacity() {
        return clubCapacity;
    }

    public void setClubCapacity(int clubCapacity) {
        this.clubCapacity = clubCapacity;
    }
}
