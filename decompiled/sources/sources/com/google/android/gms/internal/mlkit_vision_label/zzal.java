package com.google.android.gms.internal.mlkit_vision_label;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.common.collect.CollectPreconditions$$ExternalSyntheticBUOutline0;
import java.util.Objects;
import okhttp3.internal.http.RealInterceptorChain$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
abstract class zzal {
    public static int zza(int r0, String r1) {
        if (r0 < 0) goto L4;
        return r0;
    L4:
        CollectPreconditions$$ExternalSyntheticBUOutline0.m(r1, r0);
        return 0;
    }

    public static void zzb(Object r1, Object r2) {
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
