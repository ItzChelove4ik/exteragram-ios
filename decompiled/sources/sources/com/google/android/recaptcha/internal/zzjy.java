package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
final class zzjy extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzke zzb;
    final /* synthetic */ long zzc;
    private /* synthetic */ Object zzd;

    public zzjy(zzke r1, long r2, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r5, Continuation r6) {
        zzjy r0 = new zzjy(this.zzb, this.zzc, r6);
        r0.zzd = r5;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzjy) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        zzhg r4 = null;
        if (r1 == 0) goto L12;
        if (r1 == 1) goto L11;
        if (r1 == 2) goto L10;
        ResultKt.throwOnFailure(r13);     // Catch: Exception -> L8
    L20:
        long r02 = ((Number) r13).longValue();     // Catch: Exception -> L8
        long r2 = this.zzc - r02;
        if (r2 < 500) goto L26;
        return Boxing.boxLong(r2);
    L26:
        throw new zzhg(zzhf.zzc, zzhe.zzC, null, null, 12, null);     // Catch: Exception -> L8
    L10:
        ResultKt.throwOnFailure(r13);
    L17:
        zzke r132 = this.zzb;     // Catch: Exception -> L8
        zzjx r12 = new zzjx(this.zzc, r132, null);     // Catch: Exception -> L8
        this.zza = 3;     // Catch: Exception -> L8
        r13 = zzke.zzk(r132, r12, this);     // Catch: Exception -> L8
        if (r13 != r0) goto L20;
    L42:
        return r0;
    L11:
        zzmg r14 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r13);
    L14:
        this.zzd = null;
        this.zza = 2;
        if (((zzmc) r13).zza(r14, this) != r0) goto L17;
    L12:
        ResultKt.throwOnFailure(r13);
        r14 = (zzmg) this.zzd;
        zzke r133 = this.zzb;
        long r5 = this.zzc;
        this.zzd = r14;
        this.zza = 1;
        r13 = zzke.zzd(r133, r5, this);
        if (r13 == r0) goto L42;
    L8:
        e = move-exception;
        if ((e instanceof zzhg) == false) goto L30;
        r4 = (zzhg) e;
    L30:
        if (r4 != null) goto L32;
        r4 = new zzhg(zzhf.zzc, zzhe.zzC, e.getMessage(), null, 8, null);
    L32:
        zzke r122 = this.zzb;
        if (Intrinsics.areEqual(r122.zza(), zzkm.zzd()) == false) goto L35;
    L38:
        zzhg r123 = zzke.zzf(r122);
        if (r123 != null) goto L41;
        throw r4;
    L41:
        throw r123;
    L35:
        if (Intrinsics.areEqual(r122.zza(), zzkm.zzc()) == true) goto L38;
        throw r4;
    }
}
