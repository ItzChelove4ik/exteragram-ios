package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.android.dex.EncodedValueReader$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class zzjh {
    public static void zza(Bundle r4, Object r5) {
        if ((r5 instanceof Double) == false) goto L7;
        r4.putDouble("value", ((Double) r5).doubleValue());
        return;
    L7:
        if ((r5 instanceof Long) == false) goto L10;
        r4.putLong("value", ((Long) r5).longValue());
        return;
    L10:
        r4.putString("value", r5.toString());
    }

    public static Object zzb(Bundle r0, String r1, Class r2, Object r3) {
        Object r02 = r0.get(r1);
        if (r02 != null) goto L6;
        return r3;
    L6:
        if (r2.isAssignableFrom(r02.getClass()) == false) goto L8;
        return r02;
    L8:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("Invalid conditional user property field type. '%s' expected [%s] but was [%s]", new Object[]{r1, r2.getCanonicalName(), r02.getClass().getCanonicalName()});
        return null;
    }
}
