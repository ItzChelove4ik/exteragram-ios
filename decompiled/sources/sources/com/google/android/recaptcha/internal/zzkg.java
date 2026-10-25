package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
final class zzkg extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzkh zzc;
    final /* synthetic */ long zzd;
    private /* synthetic */ Object zze;

    public zzkg(zzkh r1, long r2, Continuation r4) {
        this.zzc = r1;
        this.zzd = r2;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r5, Continuation r6) {
        zzkg r0 = new zzkg(this.zzc, this.zzd, r6);
        r0.zze = r5;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzkg) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 != 0) goto L42;
        ResultKt.throwOnFailure(r12);
        zzmg r13 = (zzmg) this.zze;
        zzkh r122 = this.zzc;
        if (Intrinsics.areEqual(zzkh.zza(r122), zzkm.zzb()) == true) goto L39;
        if (Intrinsics.areEqual(zzkh.zza(r122), zzkm.zzd()) == true) goto L39;
        zzkh.zzd(r122, zzkm.zzc());
        double r3 = this.zzd;     // Catch: zzhg -> L34
        zziz r5 = zzkh.zzg(r122);     // Catch: zzhg -> L34
        double r6 = 0.4d * r3;     // Catch: zzhg -> L34
        double r32 = r3 * 0.6d;     // Catch: zzhg -> L34
        this.zze = r122;     // Catch: zzhg -> L34
        this.zza = r13;     // Catch: zzhg -> L34
        this.zzb = 1;     // Catch: zzhg -> L34
        zzkg r10 = this;
        Object r11 = r5.zza((long) r32, (long) r6, r10);     // Catch: zzhg -> L31
        if (r11 == r0) goto L33;
        zzkh r2 = r122;
        r12 = r11;
    L24:
        r10.zze = r2;     // Catch: zzhg -> L31
        r10.zza = null;     // Catch: zzhg -> L31
        r10.zzb = 2;     // Catch: zzhg -> L31
        r12 = ((zzmc) r12).zza(r13, r10);     // Catch: zzhg -> L31
        if (r12 == r0) goto L33;
        zzkh r02 = r2;
    L28:
        zzkh.zze(r02, (zzakg) r12);     // Catch: zzhg -> L31
        zzkh.zzd(r10.zzc, zzkm.zzb());     // Catch: zzhg -> L31
        return Unit.INSTANCE;
    L33:
        return r0;
    L31:
        zzhg e = e;
    L32:
        zzhg r123 = e;
    L36:
        zzkh.zzd(r10.zzc, zzkm.zzd());
        throw r123;
    L34:
        e = e;
        r10 = this;
    L39:
        return Unit.INSTANCE;
    L42:
        if (r1 == 1) goto L10;
        r02 = (zzkh) this.zze;
        ResultKt.throwOnFailure(r12);     // Catch: zzhg -> L8
        r10 = this;
        goto L28
    L10:
        r13 = (zzmg) this.zza;
        r2 = (zzkh) this.zze;
        ResultKt.throwOnFailure(r12);     // Catch: zzhg -> L8
        r10 = this;
    L8:
        e = move-exception;
        r123 = e;
        r10 = this;
        goto L36
    }
}
