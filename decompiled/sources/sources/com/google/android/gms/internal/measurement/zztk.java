package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* loaded from: classes4.dex */
final /* synthetic */ class zztk implements AsyncFunction {
    private final /* synthetic */ zzto zza;
    private final /* synthetic */ int zzb;
    private final /* synthetic */ List zzc;

    public /* synthetic */ zztk(zzto r1, int r2, List r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r3) {
        return this.zza.zzc(this.zzb, this.zzc, r3);
    }
}
