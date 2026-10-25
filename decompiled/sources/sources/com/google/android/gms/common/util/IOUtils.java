package com.google.android.gms.common.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Deprecated
/* loaded from: classes4.dex */
public abstract class IOUtils {
    public static void closeQuietly(Closeable r0) {
        if (r0 == null) goto L8;
        r0.close();     // Catch: IOException -> L5
        return;
    L9:
        return;
    }

    @Deprecated
    public static long copyStream(InputStream r7, OutputStream r8, boolean r9, int r10) {
        byte[] r0 = new byte[r10];
        long r1 = 0;
    L17:
        int r4 = r7.read(r0, 0, r10);     // Catch: Throwable -> L8
        if (r4 == (-1)) goto L10;
        r1 = r1 + r4;     // Catch: Throwable -> L8
        r8.write(r0, 0, r4);     // Catch: Throwable -> L8
        goto L17
    L10:
        if (r9 == false) goto L12;
        closeQuietly(r7);
        closeQuietly(r8);
    L12:
        return r1;
    L8:
        th = move-exception;
        if (r9 == false) goto L16;
        closeQuietly(r7);
        closeQuietly(r8);
    L16:
        throw th;
    }

    @Deprecated
    public static byte[] readInputStreamFully(InputStream r1) {
        return readInputStreamFully(r1, true);
    }

    @Deprecated
    public static byte[] readInputStreamFully(InputStream r2, boolean r3) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        copyStream(r2, r0, r3, 1024);
        return r0.toByteArray();
    }
}
