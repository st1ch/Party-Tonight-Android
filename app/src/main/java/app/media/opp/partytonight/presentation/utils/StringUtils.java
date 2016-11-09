package app.media.opp.partytonight.presentation.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    public static boolean isVowel(char c) {
        String vowels = "aeiouAEIOU";
        return vowels.contains(c + "");
    }

    public static boolean isConsanant(char c) {
        String cons = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        return cons.contains(c + "");
    }

    public static ArrayList<String> removeTheSameStrings(ArrayList<String> list) {
        ArrayList<String> result = new ArrayList<>();

        Set<String> hs = new HashSet<>();
        hs.addAll(list);

        result.addAll(hs);

        return result;
    }
}
