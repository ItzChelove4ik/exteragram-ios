package com.google.android.recaptcha.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
final class zzcy extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzcz zzd;
    final /* synthetic */ zzdo zze;
    private /* synthetic */ Object zzf;

    public zzcy(zzcz r1, zzdo r2, Continuation r3) {
        this.zzd = r1;
        this.zze = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzcy r0 = new zzcy(this.zzd, this.zze, r4);
        r0.zzf = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzcy) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzc;
        if (r1 == 0) goto L13;
        if (r1 == 1) goto L12;
        if (r1 == 2) goto L11;
        if (r1 == 3) goto L10;
        if (r1 == 4) goto L9;
        ResultKt.throwOnFailure(r9);
    L34:
        return (zzaku) r9;
    L9:
        zzmg r12 = (zzmg) this.zzf;
        ResultKt.throwOnFailure(r9);
    L30:
        this.zzf = null;
        this.zzc = 5;
        r9 = ((zzmb) r9).zza(r12, this);
        if (r9 != r0) goto L34;
    L35:
        return r0;
    L10:
        zzcz r13 = (zzcz) this.zza;
        zzmg r3 = (zzmg) this.zzf;
        ResultKt.throwOnFailure(r9);
    L27:
        zzcz.zze(r13, (zzaka) r9);
        zzdo r92 = this.zze;
        zzcz r14 = this.zzd;
        String r4 = zzcz.zzf(r14);
        zzaka r15 = zzcz.zzg(r14);
        this.zzf = r3;
        this.zza = null;
        this.zzc = 4;
        r9 = zzdo.zzf(r92, r4, r15, this);
        if (r9 == r0) goto L35;
        r12 = r3;
        goto L30
    L11:
        zzmg r16 = (zzmg) this.zzb;
        zzcz r42 = (zzcz) this.zza;
        zzmg r5 = (zzmg) this.zzf;
        ResultKt.throwOnFailure(r9);
    L24:
        this.zzf = r5;
        this.zza = r42;
        this.zzb = null;
        this.zzc = 3;
        r9 = ((zzmc) r9).zza(r16, this);
        if (r9 == r0) goto L35;
        r13 = r42;
        r3 = r5;
        goto L27
    L12:
        ResultKt.throwOnFailure(r9);
    L19:
        Object r8 = ((Result) r9).m2027unboximpl();
        ResultKt.throwOnFailure(r8);
        return (zzaku) r8;
    L13:
        ResultKt.throwOnFailure(r9);
        r16 = (zzmg) this.zzf;
        zzcz r93 = this.zzd;
        if (zzcz.zzb(r93) == true) goto L16;
    L21:
        zzng r52 = zzdo.zzj(this.zze);
        this.zzf = r16;
        this.zza = r93;
        this.zzb = r16;
        this.zzc = 2;
        Object r43 = r52.zzh(this);
        if (r43 == r0) goto L35;
        r42 = r93;
        r9 = r43;
        r5 = r16;
        goto L24
    L16:
        if (zzcz.zzc(r93) == null) goto L21;
        zzcz.zzd(r93, false);
        Deferred r94 = zzcz.zzc(r93);
        this.zzc = 1;
        r9 = r94.await(this);
        if (r9 == r0) goto L35;
        goto L19
    }
}
