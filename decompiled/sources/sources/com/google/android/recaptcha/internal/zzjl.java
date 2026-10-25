package com.google.android.recaptcha.internal;

import android.app.Application;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzjl extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ Application zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ long zzd;

    public zzjl(Application r1, String r2, long r3, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        return new zzjl(this.zzb, this.zzc, this.zzd, r8);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzjl) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r9);
        if (r1 == 0) goto L5;
        return r9;
    L5:
        Application r3 = this.zzb;
        String r4 = this.zzc;
        long r5 = this.zzd;
        zzjm r2 = zzjm.zza;
        this.zza = 1;
        Object r8 = r2.zzc(r3, r4, r5, this);
        if (r8 != r0) goto L8;
        return r0;
    L8:
        return r8;
    }
}
