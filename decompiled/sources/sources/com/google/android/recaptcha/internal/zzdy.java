package com.google.android.recaptcha.internal;

import android.app.Application;
import com.google.android.gms.recaptchabase.RecaptchaBaseClient;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
public final class zzdy extends zzcl {
    private final Application zza;
    private Deferred zzb;
    private final zzhr zzc;

    public zzdy(Application r1, zzhr r2, RecaptchaBaseClient r3) {
        this.zza = r1;
        this.zzc = r2;
    }

    public static final /* synthetic */ Application zzh(zzdy r0) {
        return r0.zza;
    }

    public static final /* synthetic */ void zzi(zzdy r0, Deferred r1) {
        r0.zzb = r1;
    }

    public static final /* synthetic */ Deferred zzj(zzdy r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ zzhr zzk(zzdy r0) {
        return r0.zzc;
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final Object zzb(zzakg r2, Continuation r3) {
        return new zzmc(new zzdx(this, r2, null));
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final Object zzc(String r2, zzaka r3, Continuation r4) {
        return new zzmc(new zzdw(this, r3, r2, null));
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final Object zzd(String r1, Continuation r2) {
        zzakt r0 = zzaku.zza();
        r0.zza(r1);
        return r0.zzp();
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final int zzf() {
        return 39;
    }

    @Override // com.google.android.recaptcha.internal.zzcl
    public final int zzg() {
        return 40;
    }
}
