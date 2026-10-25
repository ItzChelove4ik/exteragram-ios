package com.google.android.gms.internal.fido;

import java.util.Comparator;
import java.util.Objects;

/* loaded from: classes4.dex */
abstract class zzgl {
    static final String zza = null;
    static final Comparator zzb = null;

    static {
        String r0 = zzgl.class.getName().concat("$UnsafeComparator");
        zza = r0;
        Object[] r02 = Class.forName(r0).getEnumConstants();     // Catch: Throwable -> L5
        Objects.requireNonNull(r02);     // Catch: Throwable -> L5
        Comparator r03 = (Comparator) r02[0];     // Catch: Throwable -> L5
    L6:
        zzb = r03;
        return;
    L5:
        r03 = zzgk.zza;
        goto L6
    }
}
