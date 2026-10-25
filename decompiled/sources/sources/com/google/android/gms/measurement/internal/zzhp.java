package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
final /* synthetic */ class zzhp implements Callable {
    private final /* synthetic */ zzht zza;
    private final /* synthetic */ String zzb;

    public /* synthetic */ zzhp(zzht r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return new com.google.android.gms.internal.measurement.zzu("internal.appMetadata", new zzhr(this.zza, this.zzb));
    }
}
