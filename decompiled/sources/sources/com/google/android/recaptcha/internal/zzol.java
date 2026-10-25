package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzol extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzaer zzc;
    final /* synthetic */ zzaer zzd;
    final /* synthetic */ zzon zze;
    private /* synthetic */ Object zzf;

    public zzol(String r1, zzaer r2, zzaer r3, zzon r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzol r0 = new zzol(this.zzb, this.zzc, this.zzd, this.zze, r8);
        r0.zzf = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzol) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r5);
    L13:
        return (zzaka) r5;
    L6:
        zzmg r12 = (zzmg) this.zzf;
        ResultKt.throwOnFailure(r5);
    L9:
        this.zzf = null;
        this.zza = 2;
        r5 = ((zzmb) r5).zza(r12, this);
        if (r5 != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r5);
        r12 = (zzmg) this.zzf;
        String r52 = this.zzb;
        zzajz r3 = zzaka.zzg();
        r3.zza(r52);
        r3.zze(this.zzc);
        r3.zzb(this.zzd);
        zzaka r53 = (zzaka) r3.zzp();
        zzng r32 = this.zze.zzb();
        this.zzf = r12;
        this.zza = 1;
        r5 = zzoa.zzg(r32, r53, this);
        if (r5 == r0) goto L14;
        goto L9
    }
}
