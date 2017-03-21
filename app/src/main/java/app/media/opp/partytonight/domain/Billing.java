package app.media.opp.partytonight.domain;

/**
 * Created by arkadii on 11/3/16.
 */

public class Billing {
    private String billingEmail;

    public Billing(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getBillingEmail() {
        return billingEmail;
    }
}
