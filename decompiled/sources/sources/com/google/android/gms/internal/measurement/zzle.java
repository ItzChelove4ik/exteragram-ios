package com.google.android.gms.internal.measurement;

import android.net.Uri;
import androidx.collection.SimpleArrayMap;

/* loaded from: classes4.dex */
public final class zzle {
    private final SimpleArrayMap zza;

    public zzle(SimpleArrayMap r1) {
        this.zza = r1;
    }

    public final String zza(Uri r1, String r2, String r3, String r4) {
        if (r1 == null) goto L5;
        SimpleArrayMap r0 = (SimpleArrayMap) this.zza.get(r1.toString());
    L6:
        if (r0 != null) goto L9;
        return null;
    L9:
        return (String) r0.get(r4);
    L5:
        r0 = null;
        goto L6
    }
}
