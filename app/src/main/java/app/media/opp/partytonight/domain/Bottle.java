package app.media.opp.partytonight.domain;

import java.io.Serializable;

/**
 * Created by arkadii on 11/6/16.
 */

public class Bottle extends CartItem implements Serializable {

    @Override
    public String toString() {
        return "Bottle{" +
                "type='" + getType() + '\'' +
                ", price=" + getPrice() +
                ", available=" + getAvailable() +
                '}';
    }
}
