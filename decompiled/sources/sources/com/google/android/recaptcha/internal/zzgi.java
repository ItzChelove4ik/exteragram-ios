package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzgi extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzgo zzd;
    final /* synthetic */ zzakg zze;
    final /* synthetic */ zzmg zzf;
    private /* synthetic */ Object zzg;

    public zzgi(zzgo r1, zzakg r2, zzmg r3, Continuation r4) {
        this.zzd = r1;
        this.zze = r2;
        this.zzf = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzgi r0 = new zzgi(this.zzd, this.zze, this.zzf, r5);
        r0.zzg = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgi) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzc;
        if (r1 == 0) goto L15;
        if (r1 == 1) goto L14;
        if (r1 == 2) goto L13;
        if (r1 == 3) goto L12;
        if (r1 == 4) goto L11;
        if (r1 == 5) goto L10;
        ResultKt.throwOnFailure(r12);
    L32:
        Result.Companion r11 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(Unit.INSTANCE));
    L10:
        zzlp r13 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r12);
    L29:
        this.zzg = null;
        this.zzc = 6;
        if (((zzmc) r12).zza(r13.zza(), this) != r0) goto L32;
    L34:
        return r0;
    L11:
        zzalm r14 = (zzalm) this.zza;
        zzlp r3 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r12);
        zzalm r122 = r14;
        r13 = r3;
    L27:
        zzgo r32 = this.zzd;
        this.zzg = r13;
        this.zza = null;
        this.zzc = 5;
        r12 = new zzmc(new zzgh(r32, r122, null));
        if (r12 == r0) goto L34;
    L12:
        zzlp r15 = (zzlp) this.zzb;
        zzalm r4 = (zzalm) this.zza;
        zzlp r5 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r12);
        zzlp r42 = r15;
        zzalm r16 = r4;
    L24:
        this.zzg = r5;
        this.zza = r16;
        this.zzb = null;
        this.zzc = 4;
        if (((zzmc) r12).zza(r42.zza(), this) == r0) goto L34;
        r122 = r16;
        r13 = r5;
        goto L27
    L13:
        zzlp r17 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r12);
    L21:
        zzalm r123 = (zzalm) r12;
        this.zzg = r17;
        this.zza = r123;
        this.zzb = r17;
        this.zzc = 3;
        zzmc r52 = new zzmc(new zzgm(r123, null));
        if (r52 == r0) goto L34;
        r42 = r17;
        r16 = r123;
        r12 = r52;
        r5 = r42;
        goto L24
    L14:
        zzlp r18 = (zzlp) this.zza;
        zzlp r6 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r12);
    L18:
        this.zzg = r6;
        this.zza = null;
        this.zzc = 2;
        r12 = ((zzmc) r12).zza(r18.zza(), this);
        if (r12 == r0) goto L34;
        r17 = r6;
        goto L21
    L15:
        ResultKt.throwOnFailure(r12);
        r18 = (zzlp) this.zzg;
        zzgo r124 = this.zzd;
        zzakg r8 = this.zze;
        String r9 = this.zzf.zza().zza();
        this.zzg = r18;
        this.zza = r18;
        this.zzc = 1;
        r12 = new zzmc(new zzgb(r124, r9, r8, null));
        if (r12 == r0) goto L34;
        r6 = r18;
        goto L18
    }
}
