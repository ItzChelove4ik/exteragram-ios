package com.google.android.gms.measurement.internal;

import android.content.Intent;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzoy extends zzaz {
    final /* synthetic */ zzpg zza;

    public zzoy(zzpg r1, zzjg r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzaz
    public final void zza() {
        zzpg r3 = this.zza;
        r3.zzaX().zzg();
        String r0 = (String) r3.zzaz().pollFirst();
        if (r0 == null) goto L5;
        r3.zzaA(r3.zzba().elapsedRealtime());
        r3.zzaW().zzk().zzb("Sending trigger URI notification to app", r0);
        Intent r1 = new Intent();
        r1.setAction("com.google.android.gms.measurement.TRIGGERS_AVAILABLE");
        r1.setPackage(r0);
        zzpg.zzax(r3.zzaZ(), r1);
    L5:
        r3.zzaw();
    }
}
