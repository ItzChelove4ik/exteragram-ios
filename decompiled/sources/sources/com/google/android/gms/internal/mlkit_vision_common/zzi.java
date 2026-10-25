package com.google.android.gms.internal.mlkit_vision_common;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.Objects;
import okhttp3.internal.http.RealInterceptorChain$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
abstract class zzi {
    public static void zza(Object r1, Object r2) {
        if (r1 == null) goto L7;
        if (r2 == null) goto L5;
        return;
    L5:
        RealInterceptorChain$$ExternalSyntheticBUOutline0.m("null value in entry: ", r1, "=null");
        return;
    L7:
        Objects.toString(r2);
        f$$ExternalSyntheticBUOutline2.m("null key in entry: null=".concat(String.valueOf(r2)));
    }
}
