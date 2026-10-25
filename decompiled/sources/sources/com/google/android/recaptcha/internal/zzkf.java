package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
final class zzkf extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzkh zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ RecaptchaAction zzd;
    final /* synthetic */ long zze;
    private /* synthetic */ Object zzf;

    public zzkf(zzkh r1, String r2, RecaptchaAction r3, long r4, String r6, Continuation r7) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        zzkf r0 = new zzkf(this.zzb, this.zzc, this.zzd, this.zze, null, r10);
        r0.zzf = r9;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzkf) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r14);     // Catch: Exception -> L27 zzhg -> L30
    L23:
        return (String) r14;
    L7:
        zzmg r12 = (zzmg) this.zzf;
        ResultKt.throwOnFailure(r14);     // Catch: Exception -> L27 zzhg -> L30
        zzkf r122 = this;
    L19:
        r122.zzf = null;     // Catch: Exception -> L27 zzhg -> L30
        r122.zza = 2;     // Catch: Exception -> L27 zzhg -> L30
        r14 = ((zzmc) r14).zza(r12, r122);     // Catch: Exception -> L27 zzhg -> L30
        if (r14 != r0) goto L23;
    L24:
        return r0;
    L10:
        ResultKt.throwOnFailure(r14);
        r12 = (zzmg) this.zzf;
        zzkh r142 = this.zzb;     // Catch: Exception -> L27 zzhg -> L30
        if (Intrinsics.areEqual(zzkh.zza(r142), zzkm.zzb()) == false) goto L26;
        zziz r5 = zzkh.zzg(r142);     // Catch: Exception -> L27 zzhg -> L30
        String r6 = this.zzc;     // Catch: Exception -> L27 zzhg -> L30
        String r7 = this.zzd.getAction();     // Catch: Exception -> L27 zzhg -> L30
        zzakg r143 = zzkh.zzf(r142);     // Catch: Exception -> L27 zzhg -> L30
        if (r143 != null) goto L16;
        zzakg r8 = null;
    L17:
        long r9 = this.zze;     // Catch: Exception -> L27 zzhg -> L30
        this.zzf = r12;     // Catch: Exception -> L27 zzhg -> L30
        this.zza = 1;     // Catch: Exception -> L27 zzhg -> L30
        r122 = this;
        r14 = r5.zzb(r6, r7, r8, r9, null, r122);     // Catch: Exception -> L27 zzhg -> L30
        if (r14 == r0) goto L24;
    L16:
        r8 = r143;
        goto L17
    L26:
        throw new zzhg(zzhf.zzb, zzhe.zzC, null, null, 12, null);     // Catch: Exception -> L27 zzhg -> L30
    L30:
        e = move-exception;
        throw e;
    L27:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzN, e.getMessage(), null, 8, null);
    }
}
