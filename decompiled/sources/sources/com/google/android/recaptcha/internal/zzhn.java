package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes5.dex */
final class zzhn extends ContinuationImpl {
    Object zza;
    Object zzb;
    long zzc;
    long zzd;
    double zze;
    /* synthetic */ Object zzf;
    final /* synthetic */ zzhp zzg;
    int zzh;

    public zzhn(zzhp r1, Continuation r2) {
        this.zzg = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        this.zzf = r11;
        this.zzh |= Integer.MIN_VALUE;
        return this.zzg.zzc(null, 0, 0, 0.0d, null, this);
    }
}
