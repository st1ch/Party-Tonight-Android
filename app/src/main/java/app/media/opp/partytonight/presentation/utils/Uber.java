package app.media.opp.partytonight.presentation.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class Uber {
    public static void startUber(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
            String uri = "uber://?action=setPickup&pickup=my_location";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uri));
            context.startActivity(intent);
        } catch (PackageManager.NameNotFoundException e) {
            try {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.ubercab")));
            } catch (android.content.ActivityNotFoundException anfe) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.ubercab")));
            }
        }
    }
}
