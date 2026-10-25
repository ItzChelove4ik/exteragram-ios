package com.google.android.gms.internal.measurement;

import com.google.common.collect.ImmutableSet;
import java.util.UUID;

/* loaded from: classes4.dex */
public final class zzwd extends zzvn implements zzvs {
    static final zzvq zza = null;
    private final Exception zzc;

    static {
        zza = new zzvr();
    }

    private zzwd(UUID r1, String r2, Exception r3, boolean r4, zzwq r5) {
        super("<missing root>", r1, r2, r5);
        this.zzc = r3;
    }

    public static zzwd zzi(zzwq r7) {
        UUID r2 = zzvz.zza().zzc();
        String r3 = zzvn.zzcL(r2);
        ImmutableSet r0 = zzvy.zza();
        if (r0.isEmpty() == true) goto L6;
        r0.forEach(new zzwc(r2, null));
    L6:
        return new zzwd(r2, r3, zza, false, r7);
    }

    @Override // com.google.android.gms.internal.measurement.zzvs
    public final Exception zzf() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzvs
    public final zzws zzg(String r8, zzwl r9, boolean r10, zzwq r11) {
        if (r10 == false) goto L5;
        zzrg r0 = zzvy.zza;
    L5:
        return new zzwf(r8, this, r9, r10, r11);
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final zzwl zzh() {
        return zzwk.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final zzws zzj(String r1, String r2, String r3, int r4, zzwl r5, zzwq r6) {
        zzrg r22 = zzvy.zza;
        return zzg(r1, r5, true, r6);
    }

    @Override // com.google.android.gms.internal.measurement.zzws
    public final long zzk() {
        return -1;
    }
}
