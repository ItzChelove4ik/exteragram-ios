package com.exteragram.messenger.backup;

import android.text.TextUtils;
import java.nio.charset.StandardCharsets;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public abstract class InvisibleEncryptor {
    public static String decode(String r5) {
        String[] r0 = r5.replaceFirst("^\u2001\u2002", _UrlKt.FRAGMENT_ENCODE_SET).split("\u2000");     // Catch: Exception -> L5
        int r1 = r0.length;     // Catch: Exception -> L5
        byte[] r2 = new byte[r1];     // Catch: Exception -> L5
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L7;
        r2[r3] = (byte) toNum(r0[r3]);     // Catch: Exception -> L5
        r3 = r3 + 1;     // Catch: Exception -> L5
        goto L3
    L7:
        return new String(r2, StandardCharsets.UTF_8);
    L5:
        e = move-exception;
        e.printStackTrace();
        return r5;
    }

    public static String encode(String r5) {
        byte[] r0 = r5.getBytes(StandardCharsets.UTF_8);     // Catch: Exception -> L5
        int r1 = r0.length;     // Catch: Exception -> L5
        String[] r2 = new String[r1];     // Catch: Exception -> L5
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L7;
        r2[r3] = toStr(r0[r3] & UByte.MAX_VALUE);     // Catch: Exception -> L5
        r3 = r3 + 1;     // Catch: Exception -> L5
        goto L3
    L7:
        return "\u2001\u2002" + InvisibleEncryptor$$ExternalSyntheticBackport0.m("\u2000", r2);
    L5:
        e = move-exception;
        e.printStackTrace();
        return r5;
    }

    public static boolean isEncrypted(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L7;
        return false;
    L7:
        return r1.matches("^\u2001\u2002([\u200a\u200b\u200c\u200f \u206a\u206b\u206c\u206d\u206e\u206f\\s]*)");
    }

    private static int toNum(String r10) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= r10.length()) goto L6;
        int r2 = r10.length() - r0;
        r1 = (int) (r1 + ("\u200a\u200b\u200c\u200f \u206a\u206b\u206c\u206d\u206e\u206f".indexOf(r10.substring(r2 - 1, r2)) * Math.pow(11.0d, r0)));
        r0 = r0 + 1;
        goto L4
    L6:
        return r1;
    }

    private static String toStr(int r3) {
        StringBuilder r0 = new StringBuilder();
    L3:
        if (r3 <= 0) goto L6;
        r0.insert(0, "\u200a\u200b\u200c\u200f \u206a\u206b\u206c\u206d\u206e\u206f".charAt(r3 % 11));
        r3 = r3 / 11;
        goto L3
    L6:
        return r0.toString();
    }
}
