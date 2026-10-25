package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzpd {
    final String zza;
    long zzb;

    private zzpd(zzpg r1, String r2) {
        Objects.requireNonNull(r1);
        this.zza = r2;
        this.zzb = r1.zzba().elapsedRealtime();
    }

    public /* synthetic */ zzpd(zzpg r1, String r2, byte[] r3) {
        this(r1, r2);
    }

    public /* synthetic */ zzpd(zzpg r1, byte[] r2) {
        this(r1, r1.zzt().zzaz());
    }
}
