package app.media.opp.partytonight.presentation.utils;

import android.util.Log;


/**
 * Created by arkadii on 11/5/16.
 */

public class FieldsUtils {
    public static int MIN_LENGTH = 6;
    public static String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static String DIGITS = "0123456789";
    public static String EMAIL_VALID_SYMBOLS = LETTERS + DIGITS + "@._";
    public static String PASSWORD_VALID_SYMBOLS = LETTERS + DIGITS;
    public static String NAME_VALID_SYMBOLS = LETTERS + DIGITS + " ";
    public static String PHONE_VALID_SYMBOLS = DIGITS + "+";
    public static String BILLING_VALID_SYMBOLS = DIGITS + LETTERS;
    public static String CONTACT_VALID_SYMBOLS = DIGITS + LETTERS +" +";

    public static boolean hasProperLength(String field) {
        return field.length() >= MIN_LENGTH;
    }

    public static boolean isValidString(String validSymbols, String source) {
        source = source.toLowerCase();
        Log.e("FieldsUtils", source);
        boolean isValid = true;

        for (int i = 0; i < source.length(); i++) {
            if (!validSymbols.contains(String.valueOf(source.charAt(i)))) {
                Log.e("FieldsUtils", "invalid symbol " + source.charAt(i));
                isValid = false;
            }
        }
        return isValid;
    }
}
