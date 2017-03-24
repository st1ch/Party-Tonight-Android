package app.media.opp.partytonight.domain;

import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("id_transaction")
    private int id;

    @SerializedName("seller_email")
    private String sellerEmail;

    @SerializedName("billing_email")
    private String billingEmail;

    @SerializedName("subtotal")
    private double subtotal;

    @SerializedName("customer_email")
    private String customerEmail;

    @SerializedName("id_event")
    private int eventId;

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public int getEventId() {
        return eventId;
    }
}
