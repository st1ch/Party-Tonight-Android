package app.media.opp.partytonight.domain;

/**
 * Created by arkadii on 11/6/16.
 */

public class Table {
    private String type;
    private float price;
    private int quantity;

    public Table(String type, float price, int quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
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
