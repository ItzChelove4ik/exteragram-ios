package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzkh implements zzkn {
    private zzkm zza;
    private zzakg zzb;
    private final zziz zzc;

    public zzkh(zziz r1) {
        this.zzc = r1;
        this.zza = zzkm.zza();
    }

    public static final /* synthetic */ zzkm zza(zzkh r0) {
        return r0.zza;
    }

    public static final /* synthetic */ void zzd(zzkh r0, zzkm r1) {
        r0.zza = r1;
    }

    public static final /* synthetic */ void zze(zzkh r0, zzakg r1) {
        r0.zzb = r1;
    }

    public static final /* synthetic */ zzakg zzf(zzkh r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ zziz zzg(zzkh r0) {
        return r0.zzc;
    }

    @Override // com.google.android.recaptcha.internal.zzkn
    public final Object zzb(long r2, Continuation r4) {
        return new zzmc(new zzkg(this, r2, null));
    }

    @Override // com.google.android.recaptcha.internal.zzkn
    public final Object zzc(String r9, RecaptchaAction r10, long r11, String r13, Continuation r14) {
        return new zzmc(new zzkf(this, r9, r10, r11, null, null));
    }
}
