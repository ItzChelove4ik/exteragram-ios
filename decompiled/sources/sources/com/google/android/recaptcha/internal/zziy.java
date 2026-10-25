package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zziy extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zziz zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ long zze;
    private /* synthetic */ Object zzf;

    public zziy(zziz r1, long r2, long r4, Continuation r6) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r4;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        zziy r0 = new zziy(this.zzc, this.zzd, this.zze, r9);
        r0.zzf = r8;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zziy) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L12;
        if (r1 == 1) goto L11;
        if (r1 == 2) goto L10;
        if (r1 == 3) goto L9;
        zzakg r13 = (zzakg) this.zzf;
        ResultKt.throwOnFailure(r14);
        return r13;
    L9:
        zzmg r12 = (zzmg) this.zza;
        zzakg r2 = (zzakg) this.zzf;
        ResultKt.throwOnFailure(r14);
    L21:
        this.zzf = r2;
        this.zza = null;
        this.zzb = 4;
        if (((zzmc) r14).zza(r12, this) == r0) goto L24;
        return r2;
    L24:
        return r0;
    L10:
        r12 = (zzmg) this.zzf;
        ResultKt.throwOnFailure(r14);
    L18:
        zziz r3 = this.zzc;
        zzakg r11 = (zzakg) r14;
        zzdp r10 = zziz.zzd(r3).zza(r11.zzk());
        zziz.zze(r3, r10);
        long r7 = this.zze;
        zziv r9 = zziz.zzc(r3);
        this.zzf = r11;
        this.zza = r12;
        this.zzb = 3;
        r14 = new zzmc(new zziu(r7, r9, r10, r11, null));
        if (r14 == r0) goto L24;
        r2 = r11;
        goto L21
    L11:
        zzmg r15 = (zzmg) this.zza;
        zzmg r4 = (zzmg) this.zzf;
        ResultKt.throwOnFailure(r14);
    L15:
        this.zzf = r4;
        this.zza = null;
        this.zzb = 2;
        r14 = ((zzmc) r14).zza(r15, this);
        if (r14 == r0) goto L24;
        r12 = r4;
        goto L18
    L12:
        ResultKt.throwOnFailure(r14);
        r15 = (zzmg) this.zzf;
        zziz r142 = this.zzc;
        long r6 = this.zzd;
        zziv r143 = zziz.zzc(r142);
        this.zzf = r15;
        this.zza = r15;
        this.zzb = 1;
        r14 = new zzmc(new zzir(r143, r6, null));
        if (r14 == r0) goto L24;
        r4 = r15;
        goto L15
    }
}
