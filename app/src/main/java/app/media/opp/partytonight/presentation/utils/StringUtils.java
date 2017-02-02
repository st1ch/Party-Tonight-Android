package app.media.opp.partytonight.presentation.utils;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/11/16
 */

public final class StringUtils {

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }


    public static String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String getDate(long milliSeconds) {
        return getDate(milliSeconds, "hh:mm dd.MM.yy");
    }

    public static String getDate(String milliSeconds) {
        return getDate(Long.parseLong(milliSeconds), "hh:mm dd.MM.yy");
    }
}
