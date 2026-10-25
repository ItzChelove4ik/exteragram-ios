package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final /* synthetic */ class zzup implements AsyncFunction {
    private final /* synthetic */ zzut zza;
    private final /* synthetic */ AsyncFunction zzb;
    private final /* synthetic */ Executor zzc;

    public /* synthetic */ zzup(zzut r1, AsyncFunction r2, Executor r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r3) {
        return this.zza.zzd(this.zzb, this.zzc, r3);
    }
}
