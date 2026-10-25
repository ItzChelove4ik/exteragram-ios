package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
final /* synthetic */ class zzhq implements Callable {
    private final /* synthetic */ zzht zza;

    public /* synthetic */ zzhq(zzht r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return new com.google.android.gms.internal.measurement.zzt(this.zza.zzf);
    }
}
