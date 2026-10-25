package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes5.dex */
final class zzfj extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzfp zzb;
    int zzc;
    String zzd;

    public zzfj(zzfp r1, Continuation r2) {
        this.zzb = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.zza = r2;
        this.zzc |= Integer.MIN_VALUE;
        return zzfp.zzj(this.zzb, null, this);
    }
}
