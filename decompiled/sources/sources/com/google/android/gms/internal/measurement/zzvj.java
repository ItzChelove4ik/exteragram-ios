package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.SettableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzvj implements Runnable {
    private final /* synthetic */ zzvm zza;
    private final /* synthetic */ SettableFuture zzb;
    private final /* synthetic */ zzvk zzc;

    public /* synthetic */ zzvj(zzvm r1, SettableFuture r2, zzvk r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.zzb(this.zzb, this.zzc);
    }
}
