package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final /* synthetic */ class zzos implements AsyncFunction {
    private final /* synthetic */ zzpg zza;

    public /* synthetic */ zzos(zzpg r1) {
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r1) {
        return this.zza.zzj((zzmk) r1);
    }
}
