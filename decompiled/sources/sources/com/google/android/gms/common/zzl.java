package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final /* synthetic */ class zzl implements Callable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzj zzc;

    public /* synthetic */ zzl(boolean r1, String r2, zzj r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return zzo.zze(this.zza, this.zzb, this.zzc);
    }
}
