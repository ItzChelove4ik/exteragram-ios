package com.google.android.gms.internal.measurement;

import java.util.logging.Level;

/* loaded from: classes4.dex */
public abstract class zzxo extends zzxz implements zzyi {
    public zzxo(Level r1, boolean r2) {
        super(r1, false);
    }

    @Override // com.google.android.gms.internal.measurement.zzxz
    public final zzabn zza() {
        return zzabl.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzxz
    public final boolean zzb(zzyd r6) {
        zzzj r0 = zzl();
        int r1 = r0.zza();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L13;
        if (r0.zzb(r2).zzd() == "eye3tag") goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        if (r0.zzd(zzxx.zza) != null) goto L13;
        zzyl r12 = zzxx.zzi;
        if (r0.zzd(r12) != null) goto L13;
        zzm(r12, zzyv.zza);
    L13:
        return super.zzb(r6);
    }
}
