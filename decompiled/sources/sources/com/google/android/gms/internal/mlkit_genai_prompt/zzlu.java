package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.gms.internal.cast.zzhm$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
abstract class zzlu {
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

    public static int zzb(int r3, String r4) {
        if (r3 < 0) goto L4;
        return r3;
    L4:
        int r0 = r4.length();
        StringBuilder r2 = new StringBuilder((r0 + 29) + String.valueOf(r3).length());
        r2.append(r4);
        r2.append(" cannot be negative but was: ");
        r2.append(r3);
        throw new IllegalArgumentException(r2.toString());
    }
}
