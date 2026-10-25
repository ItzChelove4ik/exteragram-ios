package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final /* synthetic */ class zza implements Callable {
    private final /* synthetic */ zzc zza;

    public /* synthetic */ zza(zzc r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return new zzk(this.zza.zzc);
    }
}
