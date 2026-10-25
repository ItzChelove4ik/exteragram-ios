package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzvi implements AsyncCallable {
    private final /* synthetic */ zzvm zza;
    private final /* synthetic */ int zzb;

    public /* synthetic */ zzvi(zzvm r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.common.util.concurrent.AsyncCallable
    public final /* synthetic */ ListenableFuture call() {
        return this.zza.zzd(this.zzb);
    }
}
