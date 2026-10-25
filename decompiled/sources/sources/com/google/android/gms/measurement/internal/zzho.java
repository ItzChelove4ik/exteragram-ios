package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzho implements com.google.android.gms.internal.measurement.zzo {
    final /* synthetic */ String zza;
    final /* synthetic */ zzht zzb;

    public zzho(zzht r1, String r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zzo
    public final String zza(String r2) {
        Map r1 = (Map) this.zzb.zzE().get(this.zza);
        if (r1 != null) goto L5;
        return null;
    L5:
        if (r1.containsKey(r2) == true) goto L8;
        return null;
    L8:
        return (String) r1.get(r2);
    }
}
