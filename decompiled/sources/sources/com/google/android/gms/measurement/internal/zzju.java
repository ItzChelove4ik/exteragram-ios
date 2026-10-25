package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzju extends zzaz {
    final /* synthetic */ zzlj zza;

    public zzju(zzlj r1, zzjg r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzaz
    public final void zza() {
        zzlj r2 = this.zza.zzu.zzj();
        Objects.requireNonNull(r2);
        new Thread(new zzjt(r2)).start();
    }
}
