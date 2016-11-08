package app.media.opp.partytonight.presentation.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/8/16
 */

public final class MapUtils {

    private static int MAX_LOCATION_RESULT = 1;
    private static Locale LOCALE = Locale.ENGLISH;

    public static String getAddressLine(Context context, double latitude, double longitude) {
        Address address = getAddress(context, latitude, longitude);

        return address != null ? address.getAddressLine(0) : "";
    }

    public static String getPostalCode(Context context, double latitude, double longitude) {
        Address address = getAddress(context, latitude, longitude);

        return address != null ? address.getPostalCode() : "";
    }

    @Nullable
    public static Address getAddress(Context context, double latitude, double longitude) {
        try {

            List<Address> addresses = new Geocoder(context, LOCALE)
                    .getFromLocation(latitude, longitude, MAX_LOCATION_RESULT);

            if (!addresses.isEmpty()) {
                return addresses.get(0);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
