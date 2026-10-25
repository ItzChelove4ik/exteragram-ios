package androidx.media3.datasource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.ParserException;
import java.io.ByteArrayInputStream;

/* loaded from: classes3.dex */
public abstract class BitmapUtil {
    public static Bitmap decode(byte[] r10, int r11, BitmapFactory.Options r12, int r13) {
        if (r13 == (-1)) goto L9;
        if (r12 != null) goto L6;
        r12 = new BitmapFactory.Options();
    L6:
        r12.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(r10, 0, r11, r12);
        int r0 = Math.max(r12.outWidth, r12.outHeight);
        r12.inJustDecodeBounds = false;
        r12.inSampleSize = 1;
    L7:
        if (r0 <= r13) goto L9;
        r12.inSampleSize *= 2;
        r0 = r0 / 2;
    L9:
        Bitmap r3 = BitmapFactory.decodeByteArray(r10, 0, r11, r12);
        if (r12 == null) goto L12;
        r12.inSampleSize = 1;
    L12:
        if (r3 == null) goto L28;
        ByteArrayInputStream r112 = new ByteArrayInputStream(r10);
        ExifInterface r102 = new ExifInterface(r112);     // Catch: Throwable -> L20
        r112.close();
        int r103 = r102.getRotationDegrees();
        if (r103 == 0) goto L19;
        Matrix r8 = new Matrix();
        r8.postRotate(r103);
        return Bitmap.createBitmap(r3, 0, 0, r3.getWidth(), r3.getHeight(), r8, false);
    L19:
        return r3;
    L20:
        th = move-exception;
        r112.close();     // Catch: Throwable -> L24
        throw th;
    L24:
        th = move-exception;
        th.addSuppressed(th);
        throw th;
    L28:
        throw ParserException.createForMalformedContainer("Could not decode image data", new IllegalStateException());
    }
}
