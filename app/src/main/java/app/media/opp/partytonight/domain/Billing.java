package app.media.opp.partytonight.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arkadii on 11/3/16.
 */

public class Billing {
    private String cardNumber;

    public Billing(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
