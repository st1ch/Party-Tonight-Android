package app.media.opp.partytonight.presentation.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Singleton;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/29/16
 */
public final class FileUtils {

    private Context c;

    public FileUtils(Context c) {
        this.c = c;
    }

    public static Bitmap getBitmapFromFile(String path) {
        File imgFile = new File(path);

        if (imgFile.exists()) {

            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        return null;
    }

    public static boolean removeFile(String path) {
        if (path != null) {
            File file = new File(path);
            return file.delete();
        }
        return false;
    }

    public static void removeFiles(String paths[]) {
        for (String path : paths) {
            removeFile(path);
        }
    }

    public File createUploadableImageFile(String originalFile, int preferredSize) throws IOException {
        double max = 0.8 * Math.sqrt(Runtime.getRuntime().freeMemory() / 4);
        return saveTempFile(c, originalFile, Math.min((int) max, preferredSize));
    }

    public static File saveTempFile(Context c, String fileName, int size) throws IOException {
        Bitmap bitmap = ImageUtils.decodeSampledBitmapFromResource(fileName, size);

        File cacheDir = c.getExternalCacheDir();
        if (!cacheDir.exists()) cacheDir.mkdir();

        File result = new File(cacheDir.getPath() + File.separator
                + System.currentTimeMillis() + ".png");
        FileOutputStream fos = new FileOutputStream(result);
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
        fos.close();
        return result;
    }
}
