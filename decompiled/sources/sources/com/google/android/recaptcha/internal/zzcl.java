package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;

/* loaded from: classes5.dex */
public abstract class zzcl {
    private boolean zza;

    public zzcl() {
    }

    public static final /* synthetic */ void zze(zzcl r0, boolean r1) {
        r0.zza = r1;
    }

    public final boolean zza() {
        return this.zza;
    }

    public abstract Object zzb(zzakg r1, Continuation r2);

    public abstract Object zzc(String r1, zzaka r2, Continuation r3);

    public abstract Object zzd(String r1, Continuation r2);

    public abstract int zzf();

    public abstract int zzg();
}
