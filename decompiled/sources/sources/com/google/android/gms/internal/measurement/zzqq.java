package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final /* synthetic */ class zzqq implements Callable {
    private final /* synthetic */ zzqt zza;
    private final /* synthetic */ zzqv zzb;

    public /* synthetic */ zzqq(zzqt r1, zzqv r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        this.zza.zze(this.zzb);
        return null;
    }
}
