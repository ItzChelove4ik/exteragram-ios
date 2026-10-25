package com.google.android.gms.internal.icing;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* loaded from: classes4.dex */
public abstract class zzbp {
    public static <T> T zza(@NullableDecl T r4) {
        if (r4 == null) goto L4;
        return r4;
    L4:
        StringBuilder r0 = new StringBuilder(29);
        r0.append("expected a non-null reference", 0, 29);
        throw new zzbq(r0.toString());
    }
}
