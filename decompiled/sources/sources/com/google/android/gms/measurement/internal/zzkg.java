package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzkg extends zzaz {
    final /* synthetic */ zzlj zza;

    public zzkg(zzlj r1, zzjg r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzaz
    public final void zza() {
        zzlj r2 = this.zza;
        if (r2.zzu.zzI() == false) goto L6;
        r2.zzao().zzb(2000);
        return;
    }
}
