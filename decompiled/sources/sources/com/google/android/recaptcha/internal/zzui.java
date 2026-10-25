package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzui extends zzse {
    private final zzsp zza;

    public zzui(zzsp r1, zzsy r2) {
        zze(r1, r2);
        this.zza = r1;
    }

    private static void zze(zzsp r1, zzsy r2) {
        if (r1.zzd().equals(zzsn.zzb) == true) goto L6;
        r1.zzd().getClass();
        return;
    }

    @Override // com.google.android.recaptcha.internal.zzse
    public final zzsl zza() {
        zzsp r3 = this.zza;
        return new zzuh(r3.zzb(), r3.zze(), null);
    }

    @Override // com.google.android.recaptcha.internal.zzse
    public final Integer zzb() {
        return this.zza.zzf();
    }

    public final zzsp zzc(zzsy r1) {
        zzsp r0 = this.zza;
        zze(r0, r1);
        return r0;
    }

    public final zzady zzd() {
        zzsp r2 = this.zza;
        if (r2.zze().equals(zzso.zzd) == false) goto L7;
        return zzady.zza(new byte[0]);
    L7:
        if (r2.zze().equals(zzso.zzb) == false) goto L11;
        return zzvc.zzb(r2.zzf().intValue());
    L11:
        if (r2.zze().equals(zzso.zzc) == true) goto L18;
        if (r2.zze().equals(zzso.zze) == true) goto L18;
        zzsj$$ExternalSyntheticBUOutline1.m("Unknown output prefix type");
        return null;
    L18:
        return zzvc.zza(r2.zzf().intValue());
    }
}
