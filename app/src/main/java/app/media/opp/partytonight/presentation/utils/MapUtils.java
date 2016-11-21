package app.media.opp.partytonight.presentation.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/8/16
 */

public final class MapUtils {

    private static int MAX_LOCATION_RESULT = 1;
    private static Locale LOCALE = Locale.ENGLISH;

    private static HashMap<String, Double> latitudeCache;
    private static HashMap<String, Double> longitudeCache;

    public static String getAddressLine(Context context, double latitude, double longitude) {
        Address address = getAddress(context, latitude, longitude);

        return address != null ? address.getAddressLine(0) : "";
    }


    public static double getLatitude(Context context, String address) {
        if (latitudeCache != null && latitudeCache.containsKey(address)) {
            return latitudeCache.get(address);
        }

        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;

        double longitude = 0;
        double latitude = 0;

        try {
            addresses = geocoder.getFromLocationName(address, 1);

            if (addresses.size() > 0) {

                latitude = addresses.get(0).getLatitude();
                longitude = addresses.get(0).getLongitude();

                cache(latitudeCache, address, latitude);
                cache(longitudeCache, address, longitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return latitude;
    }

    public static double getLongitude(Context context, String address) {
        if (longitudeCache != null && longitudeCache.containsKey(address)) {
            return longitudeCache.get(address);
        }

        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;

        double longitude = 0;
        double latitude = 0;

        try {
            addresses = geocoder.getFromLocationName(address, 1);

            if (addresses.size() > 0) {

                latitude = addresses.get(0).getLatitude();
                longitude = addresses.get(0).getLongitude();

                cache(latitudeCache, address, latitude);
                cache(longitudeCache, address, longitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return longitude;
    }

    private static void cache(@Nullable HashMap<String, Double> cache, String key, Double value) {
        if (cache == null) {
            cache = new HashMap<>();
        }

        cache.put(key, value);
    }

    public static String getPostalCode(Context context, double latitude, double longitude) {
        Address address = getAddress(context, latitude, longitude);

        return address != null ? address.getPostalCode() : "";
    }

    public static String getCountryCode(Context context, double latitude, double longitude) {
        Address address = getAddress(context, latitude, longitude);

        return address != null ? address.getCountryCode() : "";
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
