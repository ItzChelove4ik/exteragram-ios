package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final /* synthetic */ class zzb implements Callable {
    private final /* synthetic */ zzc zza;

    public /* synthetic */ zzb(zzc r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return this.zza.zzg();
    }
}
