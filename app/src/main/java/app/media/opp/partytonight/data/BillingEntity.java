package app.media.opp.partytonight.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arkadii on 11/3/16.
 */

public class BillingEntity {
    @SerializedName("billing_email")
    private String billingEmail;


    public BillingEntity(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getCardNumber() {
        return billingEmail;
    }
}
