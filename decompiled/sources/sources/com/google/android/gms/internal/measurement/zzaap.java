package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/* loaded from: classes4.dex */
final class zzaap extends zzaat {
    private static final zzaap zza = null;
    private final AtomicReference zzb;

    static {
        zza = new zzaap(zzaat.zze());
    }

    public zzaap(zzaat r2) {
        this.zzb = new AtomicReference(r2);
    }

    public static final zzaap zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzaat
    public final boolean zzb(String r1, Level r2, boolean r3) {
        ((zzaat) this.zzb.get()).zzb(r1, r2, r3);
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzaat
    public final zzabe zzc() {
        return ((zzaat) this.zzb.get()).zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zzaat
    public final zzzj zzd() {
        return ((zzaat) this.zzb.get()).zzd();
    }
}
