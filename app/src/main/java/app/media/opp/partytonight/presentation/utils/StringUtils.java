package app.media.opp.partytonight.presentation.utils;

import java.text.DateFormatSymbols;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/11/16
 */

public final class StringUtils {

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }
}
