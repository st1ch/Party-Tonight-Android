package app.media.opp.partytonight.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.media.opp.partytonight.data.Ticket;

/**
 * Created by arkadii on 11/6/16.
 */

public class Event implements Serializable {
    private String clubName;
    private String partyName;
    private long time;
    private String location;
    private List<String> photos = new ArrayList<>();
    private String zipCode;
    private int clubCapacity;
    private List<Ticket> ticketPrice = new ArrayList<>();
    private List<Bottle> bottles = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();

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

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public int getClubCapacity() {
        return clubCapacity;
    }

    public void setClubCapacity(int clubCapacity) {
        this.clubCapacity = clubCapacity;
    }

    public List<Ticket> getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(List<Ticket> ticketPrice) {
        this.ticketPrice = ticketPrice;
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
}
