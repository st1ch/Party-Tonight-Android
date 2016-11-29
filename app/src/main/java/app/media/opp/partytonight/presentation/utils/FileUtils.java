package app.media.opp.partytonight.presentation.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/29/16
 */

public final class FileUtils {

    public static Bitmap getBitmapFromFile(String path) {
        File imgFile = new File(path);

        if (imgFile.exists()) {

            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        return null;
    }

    public static boolean removeFile(String path) {
        File file = new File(path);
        return file.delete();
    }
}
