package app.media.opp.partytonight.domain;

import java.io.Serializable;

/**
 * Created by arkadii on 11/6/16.
 */

public class Bottle implements Serializable {
    private String type;
    private float price;
    private int quantity;

    public Bottle() {
    }

    public Bottle(String type, float price, int quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
