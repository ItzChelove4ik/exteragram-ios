package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzju extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzke zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ RecaptchaAction zzf;
    private /* synthetic */ Object zzg;

    public zzju(zzke r1, long r2, String r4, RecaptchaAction r5, String r6, Continuation r7) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r4;
        this.zzf = r5;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        zzju r0 = new zzju(this.zzc, this.zzd, this.zze, this.zzf, null, r10);
        r0.zzg = r9;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzju) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r15) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L18;
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L12;
        if (r1 == 3) goto L9;
        ResultKt.throwOnFailure(r15);     // Catch: Exception -> L37 zzhg -> L40
    L34:
        return (String) r15;
    L9:
        zzmg r12 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r15);     // Catch: Exception -> L37 zzhg -> L40
        zzju r13 = this;
    L31:
        r13.zzg = null;     // Catch: Exception -> L37 zzhg -> L40
        r13.zzb = 4;     // Catch: Exception -> L37 zzhg -> L40
        r15 = ((zzmc) r15).zza(r12, r13);     // Catch: Exception -> L37 zzhg -> L40
        if (r15 != r0) goto L34;
    L36:
        return r0;
    L12:
        r12 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r15);     // Catch: Exception -> L37 zzhg -> L40
    L25:
        long r10 = ((Number) r15).longValue();     // Catch: Exception -> L37 zzhg -> L40
        zzke r152 = this.zzc;     // Catch: Exception -> L37 zzhg -> L40
        zziz r6 = zzke.zzn(r152);     // Catch: Exception -> L37 zzhg -> L40
        String r7 = this.zze;     // Catch: Exception -> L37 zzhg -> L40
        String r8 = this.zzf.getAction();     // Catch: Exception -> L37 zzhg -> L40
        zzakg r153 = zzke.zzm(r152);     // Catch: Exception -> L37 zzhg -> L40
        if (r153 != null) goto L28;
        zzakg r9 = null;
    L29:
        this.zzg = r12;     // Catch: Exception -> L37 zzhg -> L40
        this.zzb = 3;     // Catch: Exception -> L37 zzhg -> L40
        r13 = this;
        r15 = r6.zzb(r7, r8, r9, r10, null, r13);     // Catch: Exception -> L37 zzhg -> L40
        if (r15 == r0) goto L36;
    L28:
        r9 = r153;
        goto L29
    L15:
        zzmg r14 = (zzmg) this.zza;
        zzmg r4 = (zzmg) this.zzg;
        ResultKt.throwOnFailure(r15);     // Catch: Exception -> L37 zzhg -> L40
    L22:
        this.zzg = r4;     // Catch: Exception -> L37 zzhg -> L40
        this.zza = null;     // Catch: Exception -> L37 zzhg -> L40
        this.zzb = 2;     // Catch: Exception -> L37 zzhg -> L40
        r15 = ((zzmc) r15).zza(r14, this);     // Catch: Exception -> L37 zzhg -> L40
        if (r15 == r0) goto L36;
        r12 = r4;
        goto L25
    L18:
        ResultKt.throwOnFailure(r15);
        r14 = (zzmg) this.zzg;
        zzke r154 = this.zzc;     // Catch: Exception -> L37 zzhg -> L40
        long r62 = this.zzd;     // Catch: Exception -> L37 zzhg -> L40
        this.zzg = r14;     // Catch: Exception -> L37 zzhg -> L40
        this.zza = r14;     // Catch: Exception -> L37 zzhg -> L40
        this.zzb = 1;     // Catch: Exception -> L37 zzhg -> L40
        r15 = new zzmc(new zzjy(r154, r62, null));     // Catch: Exception -> L37 zzhg -> L40
        if (r15 == r0) goto L36;
        r4 = r14;
    L40:
        e = move-exception;
        throw e;
    L37:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzM, e.getMessage(), null, 8, null);
    }
}
