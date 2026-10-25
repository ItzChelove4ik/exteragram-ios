package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.gms.internal.fido.zzbv$$ExternalSyntheticBUOutline0;
import com.google.common.collect.CollectPreconditions$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
abstract class zzab {
    public static int zza(int r0, String r1) {
        if (r0 < 0) goto L4;
        return r0;
    L4:
        CollectPreconditions$$ExternalSyntheticBUOutline0.m(r1, r0);
        return 0;
    }

    public static void zzb(Object r0, Object r1) {
        if (r0 == null) goto L7;
        if (r1 == null) goto L5;
        return;
    L5:
        zzbv$$ExternalSyntheticBUOutline0.m(r0);
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("null key in entry: null=".concat(String.valueOf(r1)));
    }
}
