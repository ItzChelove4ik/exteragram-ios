package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
public abstract class zzlt {
    public static String zza(Context r1, String r2, String r3) {
        Preconditions.checkNotNull(r1);
        Resources r22 = r1.getResources();
        if (TextUtils.isEmpty(r3) == false) goto L6;
        r3 = zzhu.zza(r1);
    L6:
        return zzhu.zzb("google_app_id", r22, r3);
    }

    public static String zzc(String r3, String[] r4, String[] r5) {
        Preconditions.checkNotNull(r4);
        Preconditions.checkNotNull(r5);
        int r0 = Math.min(r4.length, r5.length);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L14;
        String r2 = r4[r1];
        if (r3 != null) goto L7;
        if (r2 != null) goto L7;
    L12:
        return r5[r1];
    L7:
        if (r3 == null) goto L13;
        if (r3.equals(r2) == true) goto L12;
    L13:
        r1 = r1 + 1;
        goto L3
    L14:
        return null;
    }
}
