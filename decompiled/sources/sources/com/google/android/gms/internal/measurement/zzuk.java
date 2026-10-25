package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final /* synthetic */ class zzuk implements Callable {
    private final /* synthetic */ zzul zza;

    public /* synthetic */ zzuk(zzul r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        this.zza.zza();
        return null;
    }
}
