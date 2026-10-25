package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.util.SparseArray;
import com.google.common.util.concurrent.FutureCallback;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzjw implements FutureCallback {
    final /* synthetic */ zzoh zza;
    final /* synthetic */ zzlj zzb;

    public zzjw(zzlj r1, zzoh r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // com.google.common.util.concurrent.FutureCallback
    public final void onFailure(Throwable r5) {
        zzlj r0 = this.zzb;
        r0.zzg();
        r0.zzal(false);
        r0.zzx().add(this.zza);
        int r4 = ((Integer) zzfy.zzav.zzb(null)).intValue();
        if (r0.zzam() <= r4) goto L6;
        r0.zzan(1);
        r0.zzu.zzaW().zze().zzc("registerTriggerAsync failed. May try later. App ID, throwable", zzgu.zzl(r0.zzu.zzv().zzj()), zzgu.zzl(r5.toString()));
        return;
    L6:
        r0.zzu.zzaW().zze().zzd("registerTriggerAsync failed. App ID, delay in seconds, throwable", zzgu.zzl(r0.zzu.zzv().zzj()), zzgu.zzl(String.valueOf(r0.zzam())), zzgu.zzl(r5.toString()));
        r0.zzah(r0.zzam());
        int r42 = r0.zzam();
        r0.zzan(r42 + r42);
    }

    @Override // com.google.common.util.concurrent.FutureCallback
    public final void onSuccess(Object r10) {
        zzlj r102 = this.zzb;
        r102.zzg();
        zzic r0 = r102.zzu;
        SparseArray r1 = r0.zzd().zzf();
        zzoh r9 = this.zza;
        r1.put(r9.zzc, Long.valueOf(r9.zzb));
        zzhh r2 = r0.zzd();
        int[] r3 = new int[r1.size()];
        long[] r4 = new long[r1.size()];
        int r6 = 0;
    L4:
        if (r6 >= r1.size()) goto L6;
        r3[r6] = r1.keyAt(r6);
        r4[r6] = ((Long) r1.valueAt(r6)).longValue();
        r6 = r6 + 1;
        goto L4
    L6:
        Bundle r12 = new Bundle();
        r12.putIntArray("uriSources", r3);
        r12.putLongArray("uriTimestamps", r4);
        r2.zzi.zzb(r12);
        r102.zzal(false);
        r102.zzan(1);
        r0.zzaW().zzj().zzb("Successfully registered trigger URI", r9.zza);
        r102.zzy();
    }
}
