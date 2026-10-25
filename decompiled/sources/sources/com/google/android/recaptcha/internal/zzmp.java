package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes5.dex */
final class zzmp extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzmq zzb;
    int zzc;

    public zzmp(zzmq r1, Continuation r2) {
        this.zzb = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.zza = r2;
        this.zzc |= Integer.MIN_VALUE;
        return this.zzb.zza(this);
    }
}
