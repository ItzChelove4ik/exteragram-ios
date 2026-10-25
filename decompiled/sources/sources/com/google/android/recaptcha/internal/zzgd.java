package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzgd extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzgo zzc;
    final /* synthetic */ zzakg zzd;
    private /* synthetic */ Object zze;

    public zzgd(zzgo r1, zzakg r2, Continuation r3) {
        this.zzc = r1;
        this.zzd = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzgd r0 = new zzgd(this.zzc, this.zzd, r4);
        r0.zze = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgd) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L18;
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L12;
        if (r1 == 3) goto L9;
        ResultKt.throwOnFailure(r7);     // Catch: zzhg -> L33
    L30:
        Result.Companion r6 = Result.Companion;     // Catch: zzhg -> L33
        Object r62 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: zzhg -> L33
    L36:
        return Result.m2017boximpl(r62);
    L9:
        zzmg r12 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r7);     // Catch: zzhg -> L33
    L27:
        this.zze = null;     // Catch: zzhg -> L33
        this.zzb = 4;     // Catch: zzhg -> L33
        if (((zzmc) r7).zza(r12, this) != r0) goto L30;
    L32:
        return r0;
    L12:
        r12 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r7);     // Catch: zzhg -> L33
    L25:
        zzgo r72 = this.zzc;     // Catch: zzhg -> L33
        zzakg r3 = this.zzd;     // Catch: zzhg -> L33
        zzgo.zzj(r72, r3);     // Catch: zzhg -> L33
        this.zze = r12;     // Catch: zzhg -> L33
        this.zzb = 3;     // Catch: zzhg -> L33
        r7 = zzgo.zzk(r72, r3, this);     // Catch: zzhg -> L33
        if (r7 == r0) goto L32;
    L15:
        zzmg r13 = (zzmg) this.zza;
        zzmg r4 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r7);     // Catch: zzhg -> L33
    L22:
        this.zze = r4;     // Catch: zzhg -> L33
        this.zza = null;     // Catch: zzhg -> L33
        this.zzb = 2;     // Catch: zzhg -> L33
        if (((zzmc) r7).zza(r13, this) == r0) goto L32;
        r12 = r4;
        goto L25
    L18:
        ResultKt.throwOnFailure(r7);
        r13 = (zzmg) this.zze;
        zzakg r73 = this.zzd;     // Catch: zzhg -> L33
        this.zze = r13;     // Catch: zzhg -> L33
        this.zza = r13;     // Catch: zzhg -> L33
        this.zzb = 1;     // Catch: zzhg -> L33
        r7 = new zzmc(new zzgl(r73, null));     // Catch: zzhg -> L33
        if (r7 == r0) goto L32;
        r4 = r13;
    L33:
        e = move-exception;
        Result.Companion r74 = Result.Companion;
        r62 = Result.m2018constructorimpl(ResultKt.createFailure(e));
        goto L36
    }
}
