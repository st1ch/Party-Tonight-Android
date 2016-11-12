package app.media.opp.partytonight.data;

import java.io.Serializable;

/**
 * Created by arkadii on 11/6/16.
 */

public class Ticket implements Serializable {
    private String price;

    public Ticket(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
}
