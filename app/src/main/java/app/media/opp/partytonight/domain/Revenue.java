package app.media.opp.partytonight.domain;

import java.io.Serializable;

/**
 * Created by arkadii on 12/4/16.
 */

public class Revenue implements Serializable {
    private String revenue;

    public Revenue(String revenue) {
        this.revenue = revenue;

        validate();
    }

    private void validate() {
        if (revenue == null || revenue.equals("null")) {
            this.revenue = "0";
        }
    }

    public String getRevenue() {
        validate();

        return revenue;
    }
}
