package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzjn extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzjt zzb;
    final /* synthetic */ zzmg zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ long zze;
    final /* synthetic */ zziw zzf;
    final /* synthetic */ zzmd zzg;
    private /* synthetic */ Object zzh;

    public zzjn(zzjt r1, zzmg r2, String r3, long r4, zziw r6, zzmd r7, Continuation r8) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r6;
        this.zzg = r7;
        super(2, r8);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r10, Continuation r11) {
        zzjn r0 = new zzjn(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, r11);
        r0.zzh = r10;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzjn) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r15) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L11;
        if (r1 == 1) goto L10;
        if (r1 == 2) goto L9;
        if (r1 == 3) goto L8;
        ResultKt.throwOnFailure(r15);
    L25:
        return (zzjj) r15;
    L8:
        zzlp r12 = (zzlp) this.zzh;
        ResultKt.throwOnFailure(r15);
    L21:
        this.zzh = null;
        this.zza = 4;
        r15 = ((zzmc) r15).zza(r12.zza(), this);
        if (r15 != r0) goto L25;
    L26:
        return r0;
    L9:
        ResultKt.throwOnFailure(r15);
    L18:
        return (zzjj) r15;
    L10:
        zzmg r13 = (zzmg) this.zzh;
        ResultKt.throwOnFailure(r15);
    L15:
        this.zzh = null;
        this.zza = 2;
        r15 = ((zzmb) r15).zza(r13, this);
        if (r15 == r0) goto L26;
    L11:
        ResultKt.throwOnFailure(r15);
        r12 = (zzlp) this.zzh;
        zzjt r7 = this.zzb;
        zzjj r152 = zzjt.zzd(r7);
        if (r152 == null) goto L19;
        r13 = this.zzc;
        String r2 = this.zzd;
        this.zzh = r13;
        this.zza = 1;
        r15 = new zzmb(45, new zzjs(r152, r2, null), null);
        if (r15 == r0) goto L26;
    L19:
        String r10 = this.zzd;
        long r8 = this.zze;
        zziw r11 = this.zzf;
        zzmd r122 = this.zzg;
        this.zzh = r12;
        this.zza = 3;
        r15 = new zzmc(new zzjr(r7, r8, r10, r11, r122, null));
        if (r15 == r0) goto L26;
        goto L21
    }
}
