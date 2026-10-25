package com.google.android.play.integrity.internal;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class ah {
    public static String a(byte[] r1) {
        MessageDigest r0 = MessageDigest.getInstance("SHA-256");     // Catch: NoSuchAlgorithmException -> L5
        r0.update(r1);
        return Base64.encodeToString(r0.digest(), 11);
    L5:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
