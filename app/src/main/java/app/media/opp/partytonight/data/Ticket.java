package app.media.opp.partytonight.data;

import java.io.Serializable;

/**
 * Created by arkadii on 11/6/16.
 */

public class Ticket implements Serializable {
    private String price;
    private String available;

    public Ticket(String price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "price='" + price + '\'' +
                '}';
    }
}
