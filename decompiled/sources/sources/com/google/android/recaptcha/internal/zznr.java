package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes5.dex */
final class zznr extends ContinuationImpl {
    Object zza;
    /* synthetic */ Object zzb;
    final /* synthetic */ zzns zzc;
    int zzd;
    String zze;
    String zzf;

    public zznr(zzns r1, Continuation r2) {
        this.zzc = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.zzb = r2;
        this.zzd |= Integer.MIN_VALUE;
        return this.zzc.zzb(null, null, this);
    }
}
