package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
public final class zzjs {
    final Context zza;
    Boolean zzb;
    long zzc;
    com.google.android.gms.internal.measurement.zzdb zzd;
    boolean zze;
    final Long zzf;
    final Long zzg;
    String zzh;

    public zzjs(Context r2, com.google.android.gms.internal.measurement.zzdb r3, Long r4, Long r5) {
        this.zze = true;
        Preconditions.checkNotNull(r2);
        Context r22 = r2.getApplicationContext();
        Preconditions.checkNotNull(r22);
        this.zza = r22;
        this.zzf = r4;
        this.zzg = r5;
        if (r3 == null) goto L8;
        this.zzd = r3;
        this.zze = r3.zzc;
        this.zzc = r3.zzb;
        this.zzh = r3.zze;
        Bundle r23 = r3.zzd;
        if (r23 == null) goto L9;
        this.zzb = Boolean.valueOf(r23.getBoolean("dataCollectionDefaultEnabled", true));
        return;
    L9:
        return;
    }
}
