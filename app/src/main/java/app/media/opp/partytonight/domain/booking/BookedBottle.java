package app.media.opp.partytonight.domain.booking;

import com.google.gson.annotations.SerializedName;

public class BookedBottle {

    @SerializedName("title")
    private String title;

    @SerializedName("price")
    private double price;

    @SerializedName("amount")
    private int amount;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
