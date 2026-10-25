package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
public final class zzlp {
    private final zzmg zza;
    private final Integer zzb;
    private final long zzc;
    private final int zzd;

    public zzlp(zzht r1, zzmg r2, int r3, Integer r4) {
        this.zza = r2;
        this.zzd = r3;
        this.zzb = r4;
        zzaiq.zzb(zzaiq.zzc(System.currentTimeMillis()));
        this.zzc = System.currentTimeMillis();
    }

    private final zzajh zze(int r10) {
        zzajh r0 = zzajj.zzd();
        r0.zzy(this.zzd);
        r0.zza(zzlj.zzb());
        zzmg r1 = this.zza;
        r0.zzc(r1.zzb());
        r0.zzb(r1.zza().zzb());
        r0.zzz(r1.zza().zzd());
        r0.zzA(r10);
        long r12 = this.zzc;
        r0.zzh(zzaiq.zzc(r12));
        long r3 = System.currentTimeMillis() - r12;
        long r5 = (r3 % 1000) * 1000000;
        r0.zze(zzaio.zzc(r3 / 1000, (int) r5));
        Integer r9 = this.zzb;
        if (r9 == null) goto L5;
        r0.zzw(r9.intValue());
    L5:
        return r0;
    }

    public final zzmg zza() {
        return this.zza;
    }

    public final void zzb() {
        this.zza.zzd(zze(3), null);
    }

    public final void zzc(zzhg r3) {
        zzaiz r0 = zzaja.zza();
        r0.zza(String.valueOf(r3.zza().zza()));
        r0.zzb(r3.zzb().zza());
        r0.zzd(r3.zzd().getErrorCode().getErrorCode());
        String r32 = r3.zzc();
        if (r32 == null) goto L5;
        r0.zzc(r32);
    L5:
        zzajh r33 = zze(4);
        this.zza.zzd(r33, (zzaja) r0.zzp());
    }

    public final Object zzd(int r2, Integer r3, Function2 r4, Continuation r5) {
        return new zzmb(54, r4, null).zza(this.zza, r5);
    }
}
