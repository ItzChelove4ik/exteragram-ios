package com.google.android.gms.internal.mlkit_genai_prompt;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class zzdq {
    public zzdq() {
    }

    public static zzdq zzi(zzn r10) {
        String r0 = r10.zzi();
        if (r0 != null) goto L5;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        String r2 = r10.zzb();
        String r3 = r10.zzc();
        int r4 = r10.zzd();
        int r5 = r10.zze();
        int r6 = r10.zzf();
        int r7 = r10.zzg();
        int r8 = r10.zzh();
        return new zzdu(r2, r3, r4, r5, r6, r7, r8, r0);
    }

    public abstract String zza();

    public abstract String zzb();

    public abstract int zzc();

    public abstract int zzd();

    public abstract int zze();

    public abstract int zzf();

    public abstract int zzg();

    public abstract String zzh();

    public final zzn zzj() {
        return zzn.zza(zza(), zzb(), zzc(), zzd(), zze(), zzf(), zzg(), zzh());
    }
}
