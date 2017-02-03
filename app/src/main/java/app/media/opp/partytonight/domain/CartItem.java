package app.media.opp.partytonight.domain;

import java.io.Serializable;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 2/2/17
 */
public class CartItem implements Serializable {

    private String type;
    private String price;
    private String available;
    private String booked;

    public CartItem() {
    }

    public CartItem(String type, String price, String available, String booked) {
        this.type = type;
        this.price = price;
        this.available = available;
        this.booked = booked;
    }

    public String getBooked() {
        if (booked == null) {
            booked = "0";
        }
        return booked;
    }

    public void setBooked(String booked) {
        this.booked = booked;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

}
