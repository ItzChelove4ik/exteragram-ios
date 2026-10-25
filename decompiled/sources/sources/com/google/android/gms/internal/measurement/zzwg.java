package com.google.android.gms.internal.measurement;

import java.util.UUID;

/* loaded from: classes4.dex */
public final class zzwg extends zzvt {
    public static final zzwg zza = null;

    static {
        zza = new zzwg(UUID.randomUUID());
    }

    private zzwg(UUID r7) {
        super("<skip trace>", r7, zzvn.zzcL(r7), zzwk.zza, zzvy.zzd());
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final zzws zzj(String r1, String r2, String r3, int r4, zzwl r5, zzwq r6) {
        throw new IllegalStateException("Can't create child trace for no trace!");
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final long zzk() {
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final zzwl zzl() {
        return zzwk.zza;
    }
}
