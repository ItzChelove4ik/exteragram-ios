package com.google.android.gms.internal.measurement;

import android.net.Uri;

/* loaded from: classes4.dex */
public abstract class zzuz {
    public static Uri zza(Uri r1, String r2) {
        return r1.buildUpon().path(String.valueOf(r1.getPath()).concat(r2)).build();
    }
}
