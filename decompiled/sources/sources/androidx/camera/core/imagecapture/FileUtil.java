package androidx.camera.core.imagecapture;

import android.net.Uri;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.impl.utils.Exif;
import java.io.File;
import java.io.IOException;

/* loaded from: classes3.dex */
public abstract class FileUtil {
    private static Uri copyFileToMediaStore(File r0, ImageCapture.OutputFileOptions r1) {
        throw null;
    }

    public static File createTempFile(ImageCapture.OutputFileOptions r0) {
        throw null;
    }

    private static boolean isSaveToFile(ImageCapture.OutputFileOptions r0) {
        throw null;
    }

    private static boolean isSaveToMediaStore(ImageCapture.OutputFileOptions r0) {
        throw null;
    }

    private static boolean isSaveToOutputStream(ImageCapture.OutputFileOptions r0) {
        throw null;
    }

    public static Uri moveFileToTarget(File r3, ImageCapture.OutputFileOptions r4) {
        Uri r0 = null;
        if (isSaveToMediaStore(r4) == false) goto L9;
        r0 = copyFileToMediaStore(r3, r4);     // Catch: Throwable -> L6 IOException -> L16
    L12:
        r3.delete();
        return r0;
    L9:
        if (isSaveToOutputStream(r4) == false) goto L11;
        throw null;     // Catch: Throwable -> L6
    L11:
        if (isSaveToFile(r4) == false) goto L12;
        throw null;     // Catch: Throwable -> L6
    L17:
        throw new ImageCaptureException(1, "Failed to write to OutputStream.", null);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        r3.delete();
        throw th;
    }

    public static void updateFileExif(File r0, Exif r1, ImageCapture.OutputFileOptions r2, int r3) {
        Exif r02 = Exif.createFromFile(r0);     // Catch: IOException -> L8
        r1.copyToCroppedImage(r02);     // Catch: IOException -> L8
        if (r02.getRotation() != 0) goto L7;
        if (r3 == 0) goto L7;
        r02.rotate(r3);     // Catch: IOException -> L8
    L7:
        throw null;
    L8:
        e = move-exception;
        throw new ImageCaptureException(1, "Failed to update Exif data", e);
    }
}
