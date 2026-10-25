package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes5.dex */
final class zzls extends ContinuationImpl {
    Object zza;
    /* synthetic */ Object zzb;
    final /* synthetic */ zzlx zzc;
    int zzd;

    public zzls(zzlx r1, Continuation r2) {
        this.zzc = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.zzb = r2;
        this.zzd |= Integer.MIN_VALUE;
        return zzlx.zzd(this.zzc, null, this);
    }
}
