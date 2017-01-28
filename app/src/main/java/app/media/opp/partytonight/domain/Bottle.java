package app.media.opp.partytonight.domain;

import java.io.Serializable;

/**
 * Created by arkadii on 11/6/16.
 */

public class Bottle implements Serializable {
    private String type;
    private String price;
    private String available;
    private String booked;

    public Bottle() {
    }

    public Bottle(String type, String price, String available, String booked) {
        this.type = type;
        this.price = price;
        this.available = available;
        this.booked = booked;
    }

    public String getBooked() {
        return booked;
    }

    public void setBooked(String booked) {
        this.booked = booked;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public String getAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Bottle{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
