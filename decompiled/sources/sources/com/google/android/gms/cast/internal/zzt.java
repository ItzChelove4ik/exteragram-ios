package com.google.android.gms.cast.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzt implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ zzac zzb;

    public zzt(zzw r1, zzx r2, zzac r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzJ(this.zzb);
    }
}
