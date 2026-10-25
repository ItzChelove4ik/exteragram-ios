package com.google.android.gms.cast;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzae implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ CastRemoteDisplayLocalService zzb;

    public zzae(CastRemoteDisplayLocalService r1, boolean r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
    }
}
