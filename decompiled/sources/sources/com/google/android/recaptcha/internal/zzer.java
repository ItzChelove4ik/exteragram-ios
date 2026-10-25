package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzer extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzev zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzaka zzd;
    private /* synthetic */ Object zze;

    public zzer(zzev r1, String r2, zzaka r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzer r0 = new zzer(this.zzb, this.zzc, this.zzd, r5);
        r0.zze = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzer) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r6);
        return r6;
    L7:
        zzlp r12 = (zzlp) this.zze;
        ResultKt.throwOnFailure(r6);
    L10:
        this.zze = null;
        this.zza = 2;
        Object r5 = ((zzmc) r6).zza(r12.zza(), this);
        if (r5 == r0) goto L14;
        return r5;
    L14:
        return r0;
    L8:
        ResultKt.throwOnFailure(r6);
        r12 = (zzlp) this.zze;
        zzev r62 = this.zzb;
        String r3 = this.zzc;
        zzaka r4 = this.zzd;
        this.zze = r12;
        this.zza = 1;
        r6 = r62.zzc(r3, r4, this);
        if (r6 == r0) goto L14;
        goto L10
    }
}
