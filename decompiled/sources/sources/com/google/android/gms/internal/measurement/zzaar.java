package com.google.android.gms.internal.measurement;

import java.util.Set;
import java.util.logging.Level;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzaar extends zzaag {
    private final String zza;
    private final Level zzb;
    private final Set zzc;
    private final zzzq zzd;
    private final int zze;

    public zzaar(String r1, String r2, boolean r3, int r4, boolean r5, boolean r6) {
        Level r12 = Level.ALL;
        int r32 = zzaas.$r8$clinit;
        super(r2);
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = 2;
        this.zzb = r12;
        this.zzc = zzaas.zzf();
        this.zzd = zzaas.zzg();
    }

    @Override // com.google.android.gms.internal.measurement.zzzf
    public final boolean zzb(Level r1) {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzzf
    public final void zzc(zzzd r9) {
        String r0 = (String) r9.zzl().zzd(zzyw.zza);
        if (r0 != null) goto L5;
        r0 = zza();
    L5:
        if (r0 != null) goto L9;
        r0 = r9.zzg().zza();
        int r1 = r0.indexOf(36, r0.lastIndexOf(46));
        if (r1 < 0) goto L9;
        r0 = r0.substring(0, r1);
    L9:
        String r12 = this.zza;
        Level r5 = this.zzb;
        Set r6 = this.zzc;
        zzzq r7 = this.zzd;
        zzaas.zzh(r9, zzaal.zza(r12, r0, true), 2, r5, r6, r7);
    }
}
