package com.google.firebase.crashlytics.internal.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes5.dex */
abstract class NativeSessionFileGzipper {
    private static void gzipInputStream(InputStream r4, File r5) {
        if (r4 != null) goto L4;
        return;
    L4:
        byte[] r0 = new byte[8192];
        GZIPOutputStream r1 = null;
        GZIPOutputStream r2 = new GZIPOutputStream(new FileOutputStream(r5));     // Catch: Throwable -> L14
    L19:
        int r52 = r4.read(r0);     // Catch: Throwable -> L9
        if (r52 <= 0) goto L11;
        r2.write(r0, 0, r52);     // Catch: Throwable -> L9
        goto L19
    L11:
        r2.finish();     // Catch: Throwable -> L9
        CommonUtils.closeQuietly(r2);
        return;
    L9:
        th = th;
        r1 = r2;
    L15:
        CommonUtils.closeQuietly(r1);
        throw th;
    L14:
        th = th;
        goto L15
    }

    public static void processNativeSessions(File r3, List<NativeSessionFile> r4) {
        Iterator<NativeSessionFile> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L14;
        NativeSessionFile r0 = r42.next();
        InputStream r1 = null;
        r1 = r0.getStream();     // Catch: IOException -> L15 Throwable -> L11
        if (r1 == null) goto L8;
        gzipInputStream(r1, new File(r3, r0.getReportsEndpointFilename()));     // Catch: IOException -> L15 Throwable -> L11
        goto L8
    L11:
        th = move-exception;
        CommonUtils.closeQuietly(null);
        throw th;
    L8:
        CommonUtils.closeQuietly(r1);
        goto L4
    }
}
