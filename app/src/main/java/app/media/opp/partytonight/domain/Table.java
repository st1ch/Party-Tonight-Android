package app.media.opp.partytonight.domain;

import java.io.Serializable;

/**
 * Created by arkadii on 11/6/16.
 */

public class Table extends CartItem implements Serializable {

    @Override
    public String toString() {
        return "Table{" +
                "type='" + getType() + '\'' +
                ", price=" + getPrice() +
                ", available=" + getAvailable() +
                '}';
    }
}
