package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
final /* synthetic */ class zzo implements Runnable {
    private final /* synthetic */ AppMeasurementDynamiteService zza;
    private final /* synthetic */ com.google.android.gms.internal.measurement.zzcv zzb;

    public /* synthetic */ zzo(AppMeasurementDynamiteService r1, com.google.android.gms.internal.measurement.zzcv r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zzb.zze();     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        ((zzic) Preconditions.checkNotNull(this.zza.zza)).zzaW().zze().zzb("Failed to call IDynamiteUploadBatchesCallback", e);
    }
}
