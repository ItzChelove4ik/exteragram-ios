package org.chromium.net.telemetry;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/* loaded from: classes5.dex */
public abstract class Hash {
    private static final MessageDigest MD5_MESSAGE_DIGEST = null;
    private static final String TAG = null;

    static {
        TAG = CronetLoggerImpl.class.getSimpleName();
        MD5_MESSAGE_DIGEST = getMd5MessageDigest();
    }

    private static MessageDigest getMd5MessageDigest() {
        return MessageDigest.getInstance("MD5");
    L4:
        e = move-exception;
        String r1 = TAG;
        if (Log.isLoggable(r1, 3) == false) goto L12;
        Log.d(r1, "Error while instantiating messageDigest", e);
        return null;
    L12:
        return null;
    }

    public static long hash(String r2) {
        if (MD5_MESSAGE_DIGEST == null) goto L10;
        if (r2 != null) goto L6;
        return 0;
    L6:
        if (r2.isEmpty() == false) goto L9;
        return 0;
    L9:
        return hash(r2.getBytes(StandardCharsets.UTF_8));
    L10:
        return 0;
    }

    public static long hash(byte[] r2) {
        MessageDigest r0 = MD5_MESSAGE_DIGEST;
        if (r0 == null) goto L10;
        if (r2 != null) goto L6;
        return 0;
    L6:
        if (r2.length != 0) goto L9;
        return 0;
    L9:
        return ByteBuffer.wrap(r0.digest(r2)).getLong();
    L10:
        return 0;
    }
}
