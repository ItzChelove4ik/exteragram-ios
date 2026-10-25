package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import androidx.core.provider.FontsContractCompat;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class TypefaceCompatUtil {
    public static void closeQuietly(Closeable r0) {
        if (r0 == null) goto L8;
        r0.close();     // Catch: IOException -> L5
        return;
    L9:
        return;
    }

    public static ByteBuffer copyToDirectBuffer(Context r1, Resources r2, int r3) {
        File r12 = getTempFile(r1);
        if (r12 != null) goto L15;
        return null;
    L15:
    L12:
        th = move-exception;
        r12.delete();
        throw th;
    L6:
        if (copyToFile(r12, r2, r3) == true) goto L9;
        r12.delete();
        return null;
    L9:
        ByteBuffer r22 = mmap(r12);     // Catch: Throwable -> L12
        r12.delete();
        return r22;
    }

    public static boolean copyToFile(File r0, Resources r1, int r2) {
        InputStream r12 = r1.openRawResource(r2);     // Catch: Throwable -> L8
        boolean r02 = copyToFile(r0, r12);     // Catch: Throwable -> L6
        closeQuietly(r12);
        return r02;
    L6:
        th = th;
    L10:
        closeQuietly(r12);
        throw th;
    L8:
        th = th;
        r12 = null;
        goto L10
    }

    public static boolean copyToFile(File r5, InputStream r6) {
        StrictMode.ThreadPolicy r0 = StrictMode.allowThreadDiskWrites();
        FileOutputStream r2 = null;
        FileOutputStream r3 = new FileOutputStream(r5, false);     // Catch: Throwable -> L16 IOException -> L18
        byte[] r52 = new byte[1024];     // Catch: Throwable -> L10 IOException -> L12
    L6:
        int r22 = r6.read(r52);     // Catch: Throwable -> L10 IOException -> L12
        if (r22 == (-1)) goto L14;
        r3.write(r52, 0, r22);     // Catch: Throwable -> L10 IOException -> L12
        goto L6
    L14:
        closeQuietly(r3);
        StrictMode.setThreadPolicy(r0);
        return true;
    L12:
        e = e;
        r2 = r3;
    L19:
        Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());     // Catch: Throwable -> L16
        closeQuietly(r2);
        StrictMode.setThreadPolicy(r0);
        return false;
    L10:
        th = th;
        r2 = r3;
    L22:
        closeQuietly(r2);
        StrictMode.setThreadPolicy(r0);
        throw th;
    L18:
        e = e;
    L16:
        th = th;
        goto L22
    }

    public static File getTempFile(Context r5) {
        File r52 = r5.getCacheDir();
        if (r52 != null) goto L5;
        return null;
    L5:
        String r1 = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        int r2 = 0;
    L7:
        if (r2 >= 100) goto L13;
        File r3 = new File(r52, r1 + r2);
        if (r3.createNewFile() == false) goto L12;
        return r3;
    L12:
        r2 = r2 + 1;
        goto L7
    L13:
        return null;
    }

    public static ByteBuffer mmap(Context r8, CancellationSignal r9, Uri r10) {
        ParcelFileDescriptor r82 = r8.getContentResolver().openFileDescriptor(r10, "r", r9);     // Catch: IOException -> L29
        if (r82 != null) goto L30;
        if (r82 == null) goto L7;
        r82.close();     // Catch: IOException -> L29
    L7:
        return null;
    L30:
        FileInputStream r92 = new FileInputStream(r82.getFileDescriptor());     // Catch: Throwable -> L13
        FileChannel r2 = r92.getChannel();     // Catch: Throwable -> L15
        long r6 = r2.size();     // Catch: Throwable -> L15
        MappedByteBuffer r102 = r2.map(FileChannel.MapMode.READ_ONLY, 0, r6);     // Catch: Throwable -> L15
        r92.close();     // Catch: Throwable -> L13
        r82.close();     // Catch: IOException -> L29
        return r102;
    L15:
        th = move-exception;
        r92.close();     // Catch: Throwable -> L19
    L38:
        throw th;     // Catch: Throwable -> L13
    L19:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L13
        throw th;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        r82.close();     // Catch: Throwable -> L25
    L39:
        throw th;     // Catch: IOException -> L29
    L25:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L29
        throw th;     // Catch: IOException -> L29
    L29:
        return null;
    }

    private static ByteBuffer mmap(File r8) {
        FileInputStream r1 = new FileInputStream(r8);     // Catch: IOException -> L13
        FileChannel r2 = r1.getChannel();     // Catch: Throwable -> L6
        long r6 = r2.size();     // Catch: Throwable -> L6
        MappedByteBuffer r82 = r2.map(FileChannel.MapMode.READ_ONLY, 0, r6);     // Catch: Throwable -> L6
        r1.close();     // Catch: IOException -> L13
        return r82;
    L6:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L10
    L20:
        throw th;     // Catch: IOException -> L13
    L10:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L13
        throw th;     // Catch: IOException -> L13
    L13:
        return null;
    }

    public static Map<Uri, ByteBuffer> readFontInfoIntoByteBuffer(Context r5, FontsContractCompat.FontInfo[] r6, CancellationSignal r7) {
        HashMap r0 = new HashMap();
        int r1 = r6.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L13;
        FontsContractCompat.FontInfo r3 = r6[r2];
        if (r3.getResultCode() != 0) goto L11;
        Uri r32 = r3.getUri();
        if (r0.containsKey(r32) == true) goto L11;
        r0.put(r32, mmap(r5, r7, r32));
    L11:
        r2 = r2 + 1;
        goto L3
    L13:
        return Collections.unmodifiableMap(r0);
    }
}
