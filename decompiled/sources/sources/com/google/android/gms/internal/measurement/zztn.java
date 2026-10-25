package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* loaded from: classes4.dex */
final /* synthetic */ class zztn implements AsyncFunction {
    private final /* synthetic */ zzto zza;
    private final /* synthetic */ List zzb;
    private final /* synthetic */ int zzc;

    public /* synthetic */ zztn(zzto r1, List r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r3) {
        return this.zza.zzb(this.zzb, this.zzc, (zzafc) r3);
    }
}
