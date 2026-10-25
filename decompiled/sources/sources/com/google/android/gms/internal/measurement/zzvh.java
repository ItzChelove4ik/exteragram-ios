package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzvh implements AsyncFunction {
    private final /* synthetic */ zzvm zza;
    private final /* synthetic */ int zzb;

    public /* synthetic */ zzvh(zzvm r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r2) {
        return this.zza.zzc(this.zzb, (Throwable) r2);
    }
}
