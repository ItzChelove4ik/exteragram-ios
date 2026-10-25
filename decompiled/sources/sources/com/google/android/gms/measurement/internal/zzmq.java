package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmq extends zzaz {
    final /* synthetic */ zznl zza;

    public zzmq(zznl r1, zzjg r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzaz
    public final void zza() {
        this.zza.zzu.zzaW().zze().zza("Tasks have been queued for a long time");
    }
}
