package app.media.opp.partytonight.domain.booking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by piekie (binnonnorie@gmail.com)
 * on 3/26/17
 */

public class BookedTicket {

    @SerializedName("type")
    private String type;
    @SerializedName("price")
    private double price;

    public BookedTicket() {
    }

    public BookedTicket(String type, double price) {

        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
