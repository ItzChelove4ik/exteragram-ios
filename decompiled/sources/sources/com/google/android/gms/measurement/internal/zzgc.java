package com.google.android.gms.measurement.internal;

import android.os.IBinder;
import android.os.Parcel;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzgc extends com.google.android.gms.internal.measurement.zzbl implements zzge {
    public zzgc(IBinder r2) {
        super(r2, "com.google.android.gms.measurement.internal.ITriggerUrisCallback");
    }

    @Override // com.google.android.gms.measurement.internal.zzge
    public final void zze(List r2) {
        Parcel r0 = zza();
        r0.writeTypedList(r2);
        zzd(2, r0);
    }
}
