package app.media.opp.partytonight.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arkadii on 11/3/16.
 */

public class BillingEntity {
    @SerializedName("card_number")
    private String cardNumber;


    public BillingEntity(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
