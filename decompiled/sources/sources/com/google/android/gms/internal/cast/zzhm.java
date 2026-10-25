package com.google.android.gms.internal.cast;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
abstract class zzhm {
    public static void zza(Object r0, Object r1) {
        if (r0 == null) goto L7;
        if (r1 == null) goto L5;
        return;
    L5:
        String r02 = r0.toString();
        zzhm$$ExternalSyntheticBUOutline0.m(r02.length() + 26, r02);
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("null key in entry: null=".concat(String.valueOf(r1)));
    }
}
