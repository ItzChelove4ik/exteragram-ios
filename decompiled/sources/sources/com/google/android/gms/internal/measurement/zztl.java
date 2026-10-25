package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* loaded from: classes4.dex */
final /* synthetic */ class zztl implements AsyncCallable {
    private final /* synthetic */ zzto zza;
    private final /* synthetic */ zzafc zzb;
    private final /* synthetic */ int zzc;
    private final /* synthetic */ List zzd;

    public /* synthetic */ zztl(zzto r1, zzafc r2, int r3, List r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // com.google.common.util.concurrent.AsyncCallable
    public final /* synthetic */ ListenableFuture call() {
        return this.zza.zzd(this.zzb, this.zzc, this.zzd);
    }
}
