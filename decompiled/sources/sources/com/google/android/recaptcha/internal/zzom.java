package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzom extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzakg zzb;
    final /* synthetic */ zzon zzc;
    private /* synthetic */ Object zzd;

    public zzom(zzakg r1, zzon r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzom r0 = new zzom(this.zzb, this.zzc, r4);
        r0.zzd = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzom) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r6);
    L13:
        return Unit.INSTANCE;
    L6:
        zzmg r12 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r6);
    L9:
        this.zzd = null;
        this.zza = 2;
        if (((zzmb) r6).zza(r12, this) != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r6);
        r12 = (zzmg) this.zzd;
        zzakg r62 = this.zzb;
        zzajz r3 = zzaka.zzg();
        r3.zza(r62.zzd().zzb());
        r3.zze(r62.zzg().zza());
        r3.zzb(r62.zzd().zzd());
        r3.zzc(r62.zzd().zze());
        r3.zzd(r62.zzd().zzg());
        zzaka r63 = (zzaka) r3.zzp();
        zzng r32 = this.zzc.zzb();
        this.zzd = r12;
        this.zza = 1;
        r6 = zzoa.zze(r32, r63, this);
        if (r6 == r0) goto L14;
        goto L9
    }
}
