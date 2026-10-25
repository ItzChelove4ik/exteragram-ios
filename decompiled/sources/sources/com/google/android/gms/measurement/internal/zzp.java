package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzp implements zzjp {
    public final com.google.android.gms.internal.measurement.zzcy zza;
    final /* synthetic */ AppMeasurementDynamiteService zzb;

    public zzp(AppMeasurementDynamiteService r1, com.google.android.gms.internal.measurement.zzcy r2) {
        Objects.requireNonNull(r1);
        this.zzb = r1;
        this.zza = r2;
    }

    @Override // com.google.android.gms.measurement.internal.zzjp
    public final void interceptEvent(String r7, String r8, Bundle r9, long r10) {
        this.zza.zze(r7, r8, r9, r10);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzic r6 = this.zzb.zza;
        if (r6 == null) goto L11;
        r6.zzaW().zze().zzb("Event interceptor threw exception", e);
        return;
    }
}
