package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzir extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zziv zzc;
    final /* synthetic */ long zzd;
    private /* synthetic */ Object zze;

    public zzir(zziv r1, long r2, Continuation r4) {
        this.zzc = r1;
        this.zzd = r2;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r5, Continuation r6) {
        zzir r0 = new zzir(this.zzc, this.zzd, r6);
        r0.zze = r5;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzir) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L16;
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L14;
        if (r1 == 3) goto L13;
        if (r1 == 4) goto L12;
        if (r1 == 5) goto L11;
        zzakg r11 = (zzakg) this.zze;
        ResultKt.throwOnFailure(r12);
        return r11;
    L11:
        zzmg r13 = (zzmg) this.zza;
        zzakg r2 = (zzakg) this.zze;
        ResultKt.throwOnFailure(r12);
    L33:
        this.zze = r2;
        this.zza = null;
        this.zzb = 6;
        if (r13.zzc((zzmb) r12, this) == r0) goto L37;
        return r2;
    L37:
        return r0;
    L12:
        r13 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r12);
    L28:
        zzakg r122 = (zzakg) r12;
        if (r122.zzl() == false) goto L36;
        zziq r4 = new zziq(this.zzc, null);
        this.zze = r122;
        this.zza = r13;
        this.zzb = 5;
        zzmb r22 = new zzmb(88, r4, null);
        if (r22 == r0) goto L37;
        r2 = r122;
        r12 = r22;
        goto L33
    L36:
        return r122;
    L13:
        zzmg r14 = (zzmg) this.zza;
        zzmg r42 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r12);
    L25:
        this.zze = r42;
        this.zza = null;
        this.zzb = 4;
        r12 = ((zzmb) r12).zza(r14, this);
        if (r12 == r0) goto L37;
        r13 = r42;
        goto L28
    L14:
        r14 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r12);
    L22:
        zziv r5 = this.zzc;
        long r8 = this.zzd;
        zzkx r52 = zziv.zzb(r5);
        this.zze = r14;
        this.zza = r14;
        this.zzb = 3;
        r12 = r52.zza((zzamf) r12, r8, this);
        if (r12 == r0) goto L37;
        r42 = r14;
        goto L25
    L15:
        zzmg r15 = (zzmg) this.zza;
        zzmg r6 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r12);
    L19:
        this.zze = r6;
        this.zza = null;
        this.zzb = 2;
        r12 = ((zzmc) r12).zza(r15, this);
        if (r12 == r0) goto L37;
        r14 = r6;
        goto L22
    L16:
        ResultKt.throwOnFailure(r12);
        r15 = (zzmg) this.zze;
        zzif r123 = zziv.zza(this.zzc);
        this.zze = r15;
        this.zza = r15;
        this.zzb = 1;
        r12 = new zzmc(new zzie(r123, null));
        if (r12 == r0) goto L37;
        r6 = r15;
        goto L19
    }
}
