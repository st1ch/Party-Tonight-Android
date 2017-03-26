package app.media.opp.partytonight.domain.booking;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.media.opp.partytonight.domain.CartItemExtended;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 3/21/17
 */

public class Booking {

    @SerializedName("id_event")
    private int idEvent;

    @SerializedName("bottles")
    private List<BookedBottle> bottles = new ArrayList<>();

    @SerializedName("table")
    private BookedTable table;

    @SerializedName("ticket")
    private BookedTicket ticket;

    private transient HashMap<String, BookedBottle> bottlesAsMap = new HashMap<>();

    public Booking(int idEvent, BookedBottle bottle) {
        this.idEvent = idEvent;

        bottlesAsMap.put(bottle.getTitle(), bottle);
    }

    public Booking(int idEvent, BookedTable bookedTable) {
        this.idEvent = idEvent;
        this.table = bookedTable;
    }


    public Booking(int idEvent, BookedTicket ticket) {
        this.idEvent = idEvent;
        this.ticket = ticket;
    }
    public Booking() {

    }

    public List<BookedBottle> getBottles() {
        return bottles;
    }

    public void setBottles(List<BookedBottle> bottles) {
        this.bottles = bottles;
    }

    public BookedTable getTable() {
        return table;
    }

    public void setTable(BookedTable table) {
        this.table = table;
    }

    public BookedTicket getTicket() {
        return ticket;
    }

    public void setTicket(BookedTicket ticket) {
        this.ticket = ticket;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public HashMap<String, BookedBottle> getBottlesAsMap() {
        return bottlesAsMap;
    }

    public void setBottlesAsMap(HashMap<String, BookedBottle> bottlesAsMap) {
        this.bottlesAsMap = bottlesAsMap;
    }

    public List<CartItemExtended> toCartItems() {
        List<CartItemExtended> cartItems = new ArrayList<>();

        for (BookedBottle bottle : bottles) {
            CartItemExtended item = new CartItemExtended();

            item.setAmount(bottle.getAmount());
            item.setFullPrice(bottle.getPrice() * bottle.getAmount());
            item.setTypeOfItem(CartItemExtended.Type.Bottle);
            item.setTitle(bottle.getTitle());

            cartItems.add(item);
        }

        if (table != null) {
            CartItemExtended tableItem = new CartItemExtended();
            tableItem.setFullPrice(table.getPrice());
            tableItem.setTitle(table.getType());
            tableItem.setNumber(table.getNumber());
            tableItem.setTypeOfItem(CartItemExtended.Type.Table);

            cartItems.add(tableItem);
        }

        if (ticket != null) {
            CartItemExtended ticketItem = new CartItemExtended();
            ticketItem.setFullPrice(ticket.getPrice());
            ticketItem.setTitle(ticket.getType());
            ticketItem.setTypeOfItem(CartItemExtended.Type.Ticket);

            cartItems.add(ticketItem);
        }

        return cartItems;

    }
}