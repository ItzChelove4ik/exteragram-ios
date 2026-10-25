package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Ref;

/* loaded from: classes5.dex */
final class zzhm extends ContinuationImpl {
    long zza;
    double zzb;
    Object zzc;
    int zzd;
    int zze;
    /* synthetic */ Object zzf;
    final /* synthetic */ zzhp zzg;
    int zzh;
    Ref.LongRef zzi;

    public zzhm(zzhp r1, Continuation r2) {
        this.zzg = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        this.zzf = r11;
        this.zzh |= Integer.MIN_VALUE;
        return this.zzg.zza(0, 0, 0, 0.0d, null, this);
    }
}
