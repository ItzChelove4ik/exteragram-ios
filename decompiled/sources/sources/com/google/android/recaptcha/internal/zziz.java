package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zziz {
    private final zziv zza;
    private final zzio zzb;
    private final zzlg zzc;
    private zzdp zzd;

    public zziz(zziv r1, zzio r2, zzlg r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public static final /* synthetic */ zziv zzc(zziz r0) {
        return r0.zza;
    }

    public static final /* synthetic */ zzlg zzd(zziz r0) {
        return r0.zzc;
    }

    public static final /* synthetic */ void zze(zziz r0, zzdp r1) {
        r0.zzd = r1;
    }

    public static final /* synthetic */ zzdp zzf(zziz r0) {
        return r0.zzd;
    }

    public static final /* synthetic */ zzio zzg(zziz r0) {
        return r0.zzb;
    }

    public final Object zza(long r8, long r10, Continuation r12) {
        return new zzmc(new zziy(this, r8, r10, null));
    }

    public final Object zzb(String r10, String r11, zzakg r12, long r13, String r15, Continuation r16) {
        return new zzmc(new zzix(this, r10, r11, r12, r13, null, null));
    }
}
