package app.media.opp.partytonight.presentation.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;

public class ResourcesUtils {

    public static Drawable getDrawable(Context context, int resId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(resId);
        } else {
            return context.getDrawable(resId);
        }
    }

    public static int getColorFromAttr(Context context, int attrId) {
        int[] attribute = new int[] { attrId };
        TypedArray array = context.getTheme().obtainStyledAttributes(attribute);
        int color = array.getColor(0, Color.RED);
        array.recycle();
        return color;
    }

    public static int getColor(Context c, int resourceId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return c.getColor(resourceId);
        } else {
            return c.getResources().getColor(resourceId);
        }
    }

    public static int dpToPx(Context c, int dp) {
        return (int) (c.getResources().getDisplayMetrics().density * dp);
    }


    public static int getDisplayWidth(Context c){
        DisplayMetrics metrics = c.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getDisplayHeight(Context c){
        DisplayMetrics metrics = c.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static int getDimenInPx(Context c, int resId) {
        return (int) c.getResources().getDimension(resId);
    }

}
