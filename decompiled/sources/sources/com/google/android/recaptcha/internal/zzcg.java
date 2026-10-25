package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzcg extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzcl zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzaka zzd;
    final /* synthetic */ long zze;
    private /* synthetic */ Object zzf;

    public zzcg(zzcl r1, String r2, zzaka r3, long r4, Continuation r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        zzcg r0 = new zzcg(this.zzb, this.zzc, this.zzd, this.zze, r9);
        r0.zzf = r8;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzcg) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L12;
        if (r1 == 1) goto L9;
        if (r1 == 2) goto L7;
        ResultKt.throwOnFailure(r13);
    L24:
        Object r12 = Result.m2018constructorimpl(r13);
    L26:
        return Result.m2017boximpl(r12);
    L7:
        ResultKt.throwOnFailure(r13);     // Catch: Exception -> L21
    L19:
        r12 = Result.m2018constructorimpl((zzaku) r13);     // Catch: Exception -> L21
        goto L26
    L9:
        zzmg r14 = (zzmg) this.zzf;
        ResultKt.throwOnFailure(r13);     // Catch: Exception -> L21
    L17:
        this.zzf = null;     // Catch: Exception -> L21
        this.zza = 2;     // Catch: Exception -> L21
        r13 = ((zzmb) r13).zza(r14, this);     // Catch: Exception -> L21
        if (r13 != r0) goto L19;
    L23:
        return r0;
    L12:
        ResultKt.throwOnFailure(r13);
        r14 = (zzmg) this.zzf;
        zzcl r8 = this.zzb;
        if (r8.zza() == false) goto L28;
        String r9 = this.zzc;     // Catch: Exception -> L21
        zzaka r10 = this.zzd;     // Catch: Exception -> L21
        long r6 = this.zze;     // Catch: Exception -> L21
        this.zzf = r14;     // Catch: Exception -> L21
        this.zza = 1;     // Catch: Exception -> L21
        zzmb r3 = new zzmb(r8.zzg(), new zzck(r6, r8, r9, r10, null), null);     // Catch: Exception -> L21
        if (r3 == r0) goto L23;
        r13 = r3;
        goto L17
    L28:
        return Result.m2017boximpl(Result.m2018constructorimpl(zzaku.zzb()));
    L21:
        Result.Companion r132 = Result.Companion;
        zzcl r133 = this.zzb;
        String r15 = this.zzc;
        this.zzf = null;
        this.zza = 3;
        r13 = r133.zzd(r15, this);
        if (r13 != r0) goto L24;
        goto L23
    }
}
