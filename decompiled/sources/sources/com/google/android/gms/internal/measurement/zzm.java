package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzm extends zzai {
    final /* synthetic */ zzo zza;

    public zzm(zzn r1, String r2, zzo r3) {
        this.zza = r3;
        Objects.requireNonNull(r1);
        super("getValue");
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg r3, List r4) {
        zzh.zza("getValue", 2, r4);
        zzao r0 = r3.zza((zzao) r4.get(0));
        zzao r32 = r3.zza((zzao) r4.get(1));
        String r42 = r0.zzc();
        String r2 = this.zza.zza(r42);
        if (r2 != null) goto L5;
        return r32;
    L5:
        return new zzas(r2);
    }
}
