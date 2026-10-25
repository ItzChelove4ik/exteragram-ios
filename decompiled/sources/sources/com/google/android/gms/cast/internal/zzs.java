package com.google.android.gms.cast.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzs implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ int zzb;

    public zzs(zzw r1, zzx r2, int r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzQ().onApplicationDisconnected(this.zzb);
    }
}
