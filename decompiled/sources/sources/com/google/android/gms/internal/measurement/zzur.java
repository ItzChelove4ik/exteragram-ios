package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final /* synthetic */ class zzur implements AsyncFunction {
    private final /* synthetic */ zzus zza;
    private final /* synthetic */ AsyncFunction zzb;
    private final /* synthetic */ Executor zzc;

    public /* synthetic */ zzur(zzus r1, AsyncFunction r2, Executor r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r3) {
        Void r32 = (Void) r3;
        return this.zza.zza.zzf().zzb(this.zzb, this.zzc, null);
    }
}
