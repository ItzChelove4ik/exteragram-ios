package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes5.dex */
final class zzjd extends ContinuationImpl {
    /* synthetic */ Object zza;
    final /* synthetic */ zzjj zzb;
    int zzc;

    public zzjd(zzjj r1, Continuation r2) {
        this.zzb = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        this.zza = r4;
        this.zzc |= Integer.MIN_VALUE;
        Object r3 = zzjj.zzc(this.zzb, null, 0, this);
        if (r3 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r3;
    L6:
        return Result.m2017boximpl(r3);
    }
}
