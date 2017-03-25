package app.media.opp.partytonight.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 3/21/17
 */

public class Booking {

    private String partyName;
    private List<Bottle> bottles = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();
    private int tickets;

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
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

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
}
