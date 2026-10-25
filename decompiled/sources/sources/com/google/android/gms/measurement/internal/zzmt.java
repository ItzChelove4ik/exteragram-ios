package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmt implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzbh zzc;
    final /* synthetic */ zznl zzd;

    public zzmt(zznl r1, boolean r2, zzr r3, boolean r4, zzbh r5, String r6) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznl r0 = this.zzd;
        zzgb r1 = r0.zzZ();
        if (r1 != null) goto L6;
        r0.zzu.zzaW().zzb().zza("Discarding data. Failed to send event to service");
        return;
    L6:
        zzr r2 = this.zza;
        Preconditions.checkNotNull(r2);
        if (this.zzb == false) goto L9;
        zzbh r4 = null;
    L10:
        r0.zzm(r1, r4, r2);
        r0.zzV();
        return;
    L9:
        r4 = this.zzc;
        goto L10
    }
}
