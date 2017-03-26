package app.media.opp.partytonight.domain.booking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 3/26/17
 */

public class BookedTable {

    @SerializedName("type")
    private String type;

    @SerializedName("number")
    private int number;

    @SerializedName("price")
    private double price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
