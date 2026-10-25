package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public final class zzcx implements zzdp {
    private final zzct zza;

    public zzcx(zzct r1) {
        this.zza = r1;
    }

    public static final /* synthetic */ zzct zzd(zzcx r0) {
        return r0.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzdp
    public final Object zza(long r7, zzakg r9, Continuation r10) {
        return new zzmb(30, new zzcv(this, r7, r9, null), null);
    }

    @Override // com.google.android.recaptcha.internal.zzdp
    public final Object zzb(String r1, String r2, zzaka r3, long r4, Continuation r6) {
        return new zzmb(31, new zzcu(this, r1, r3, r4, null), null);
    }

    @Override // com.google.android.recaptcha.internal.zzdp
    public final Object zzc(String r1, String r2, zzaka r3, Continuation r4) {
        return new zzmc(new zzcw(null));
    }
}
