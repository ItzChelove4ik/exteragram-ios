package com.google.android.gms.internal.measurement;

import java.util.Set;
import java.util.logging.Level;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzaaq implements zzaai {
    private final String zza;
    private final Level zzb;
    private final Set zzc;
    private final zzzq zzd;
    private final int zze;

    private zzaaq(String r1, boolean r2, int r3, Level r4, boolean r5, Set r6, zzzq r7) {
        this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zze = 2;
        this.zzb = r4;
        this.zzc = r6;
        this.zzd = r7;
    }

    public /* synthetic */ zzaaq(byte[] r9) {
        Set r6 = zzaas.zzf();
        zzzq r7 = zzaas.zzg();
        this(_UrlKt.FRAGMENT_ENCODE_SET, true, 2, Level.ALL, false, r6, r7);
    }

    @Override // com.google.android.gms.internal.measurement.zzaai
    public final zzzf zza(String r10) {
        Level r5 = this.zzb;
        Set r6 = this.zzc;
        zzzq r7 = this.zzd;
        return new zzaas(this.zza, r10, true, 2, r5, r6, r7, null);
    }

    public final zzaaq zzb(boolean r9) {
        Set r6 = this.zzc;
        zzzq r7 = this.zzd;
        Level r4 = Level.OFF;
        return new zzaaq(this.zza, true, 2, r4, false, r6, r7);
    }
}
