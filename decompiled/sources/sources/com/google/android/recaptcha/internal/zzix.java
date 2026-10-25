package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzix extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zziz zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzakg zze;
    final /* synthetic */ long zzf;
    private /* synthetic */ Object zzg;

    public zzix(zziz r1, String r2, String r3, zzakg r4, long r5, String r7, Continuation r8) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r5;
        super(2, r8);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r10, Continuation r11) {
        zzix r0 = new zzix(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, null, r11);
        r0.zzg = r10;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzix) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r14);
        return r14;
    L7:
        zzmg r12 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r14);
    L12:
        this.zzg = null;
        this.zza = 2;
        Object r13 = ((zzmc) r14).zza(r12, this);
        if (r13 == r0) goto L16;
        return r13;
    L16:
        return r0;
    L8:
        ResultKt.throwOnFailure(r14);
        r12 = (zzmg) this.zzg;
        zziz r142 = this.zzb;
        zzdp r7 = zziz.zzf(r142);
        if (r7 == null) goto L18;
        zzio r6 = zziz.zzg(r142);
        String r8 = this.zzc;
        String r10 = this.zzd;
        zzakg r9 = this.zze;
        long r4 = this.zzf;
        this.zzg = r12;
        this.zza = 1;
        r14 = new zzmc(new zzim(r4, r6, r7, r8, r9, r10, null, null));
        if (r14 == r0) goto L16;
    L18:
        throw new zzhg(zzhf.zzb, zzhe.zzC, null, null, 12, null);
    }
}
