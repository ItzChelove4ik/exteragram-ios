package androidx.exifinterface.media;

import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
abstract class ExifInterfaceUtils {

    public static class Api23Impl {
        public static void setDataSource(MediaMetadataRetriever r0, MediaDataSource r1) {
            r0.setDataSource(r1);
        }
    }

    public static void closeFileDescriptor(FileDescriptor r2) {
        Os.close(r2);     // Catch: ErrnoException -> L4
        return;
    L4:
        e = move-exception;
        Log.e("ExifInterfaceUtils", "Error closing fd.", e);
    }

    public static void closeQuietly(Closeable r0) {
        if (r0 != null) goto L9;
        return;
    L9:
        r0.close();     // Catch: Exception -> L8 RuntimeException -> L5
        return;
    L5:
        e = move-exception;
        throw e;
    }

    public static long[] convertToLongArray(Object r4) {
        if ((r4 instanceof int[]) == false) goto L10;
        int[] r42 = (int[]) r4;
        long[] r0 = new long[r42.length];
        int r1 = 0;
    L6:
        if (r1 >= r42.length) goto L8;
        r0[r1] = r42[r1];
        r1 = r1 + 1;
        goto L6
    L8:
        return r0;
    L10:
        if ((r4 instanceof long[]) == true) goto L12;
        return null;
    L12:
        return (long[]) r4;
    }

    public static int copy(InputStream r5, OutputStream r6) {
        byte[] r0 = new byte[8192];
        int r2 = 0;
    L3:
        int r3 = r5.read(r0);
        if (r3 == (-1)) goto L6;
        r2 = r2 + r3;
        r6.write(r0, 0, r3);
        goto L3
    L6:
        return r2;
    }

    public static void copy(InputStream r5, OutputStream r6, int r7) {
        byte[] r1 = new byte[8192];
    L3:
        if (r7 <= 0) goto L11;
        int r2 = Math.min(r7, 8192);
        int r4 = r5.read(r1, 0, r2);
        if (r4 != r2) goto L7;
        r7 = r7 - r4;
        r6.write(r1, 0, r4);
        goto L3
    L7:
        Model$$ExternalSyntheticBUOutline0.m("Failed to copy the given amount of bytes from the inputstream to the output stream.");
        return;
    }

    public static boolean startsWith(byte[] r4, byte[] r5) {
        if (r4 == null) goto L18;
        if (r5 == null) goto L18;
        if (r4.length >= r5.length) goto L9;
        return false;
    L9:
        int r1 = 0;
    L11:
        if (r1 >= r5.length) goto L16;
        if (r4[r1] != r5[r1]) goto L14;
        r1 = r1 + 1;
        goto L11
    L14:
        return false;
    L16:
        return true;
    L18:
        return false;
    }
}
