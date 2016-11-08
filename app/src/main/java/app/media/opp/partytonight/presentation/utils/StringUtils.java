package app.media.opp.partytonight.presentation.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/8/16
 */

public final class StringUtils {

    public static String repeat(String line, int amount) {
        String result = "";

        for (int i = 0; i < amount; i++) {
            result += line;
        }

        return result;
    }

    public static String cutDouble(double value, int precision) {
        DecimalFormat df = new DecimalFormat("#." + repeat("#", precision));

        df.setRoundingMode(RoundingMode.CEILING);

        return df.format(value);

    }
}
