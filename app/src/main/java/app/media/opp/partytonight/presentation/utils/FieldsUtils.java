package app.media.opp.partytonight.presentation.utils;

/**
 * Created by arkadii on 11/5/16.
 */

public class FieldsUtils {
    public static int MIN_LENGTH = 6;
    public static String LETTERS = "abcdefghigklmnopqrstuvwxyz";
    public static String DIGITS = "0123456789";
    public static String EMAIL_VALID_SYMBOLS = LETTERS + DIGITS + "@.";
    public static String PASSWORD_VALID_SYMBOLS = LETTERS + DIGITS;


    public static boolean hasProperLength(String field) {
        return field.length() >= 6;
    }

    public static boolean isValidString(String validSymbols, String source) {
        boolean isValid = true;
        for (int i = 0; i < source.length(); i++) {
            if (!validSymbols.contains(String.valueOf(source.charAt(i)))) {
                isValid = false;
            }
        }
        return isValid;
    }
}
