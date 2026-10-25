package com.google.android.recaptcha.internal;

import android.os.Build;
import java.nio.charset.Charset;
import java.util.Objects;

/* loaded from: classes5.dex */
public abstract class zzwc {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        Charset.forName("UTF-8");
    }

    public static boolean zza() {
        return Objects.equals(System.getProperty("java.vendor"), "The Android Project");
    }

    public static Integer zzb() {
        if (zza() == true) goto L7;
        return null;
    L7:
        return Integer.valueOf(Build.VERSION.SDK_INT);
    }

    public static boolean zzc(byte[] r4, byte[] r5) {
        if (r5.length < r4.length) goto L13;
        int r0 = 0;
    L6:
        if (r0 >= r4.length) goto L11;
        if (r5[r0] != r4[r0]) goto L9;
        r0 = r0 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    L13:
        return false;
    }
}
