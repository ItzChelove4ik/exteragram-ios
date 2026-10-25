package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
public final class zzph {
    final Context zza;

    public zzph(Context r1) {
        Preconditions.checkNotNull(r1);
        Context r12 = r1.getApplicationContext();
        Preconditions.checkNotNull(r12);
        this.zza = r12;
    }
}
