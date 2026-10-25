package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public abstract class zztj {
    public static void zza(long[] r5, long[] r6, int r7) {
        int r0 = 0;
    L4:
        if (r0 >= 10) goto L6;
        int r2 = (int) r5[r0];
        r5[r0] = ((-r7) & (((int) r6[r0]) ^ r2)) ^ r2;
        r0 = r0 + 1;
        goto L4
    }
}
