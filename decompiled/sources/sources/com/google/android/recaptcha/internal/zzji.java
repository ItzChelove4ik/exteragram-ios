package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzji extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzjj zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ RecaptchaAction zzd;
    private /* synthetic */ Object zze;

    public zzji(zzjj r1, long r2, RecaptchaAction r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzji r0 = new zzji(this.zzb, this.zzc, this.zzd, r8);
        r0.zze = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzji) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r19) {
        Object r7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.zza;
        if (r0 == 0) goto L10;
        if (r0 == 1) goto L7;
        ResultKt.throwOnFailure(r19);     // Catch: Exception -> L31 zzhg -> L34
        Object r02 = r19;
    L24:
        String r03 = (String) r02;     // Catch: Exception -> L31 zzhg -> L34
        if (r03.length() == 0) goto L28;
        return r03;
    L28:
        throw new zzhg(zzhf.zzb, zzhe.zzam, null, null, 12, null);     // Catch: Exception -> L31 zzhg -> L34
    L7:
        zzlp r04 = (zzlp) this.zze;
        ResultKt.throwOnFailure(r19);     // Catch: Exception -> L31 zzhg -> L34
        zzlp r9 = r04;
        Object r05 = r19;
    L21:
        this.zze = null;     // Catch: Exception -> L31 zzhg -> L34
        this.zza = 2;     // Catch: Exception -> L31 zzhg -> L34
        r02 = ((zzmc) r05).zza(r9.zza(), this);     // Catch: Exception -> L31 zzhg -> L34
        if (r02 != r7) goto L24;
    L29:
        return r7;
    L10:
        ResultKt.throwOnFailure(r19);
        r9 = (zzlp) this.zze;
        long r3 = this.zzc;     // Catch: Exception -> L31 zzhg -> L34
        RecaptchaAction r2 = this.zzd;     // Catch: Exception -> L31 zzhg -> L34
        if (zzja.zza().matches(r2.getAction()) == true) goto L14;
        zzhg r10 = new zzhg(zzhf.zzg, zzhe.zzh, null, null, 12, null);     // Catch: Exception -> L31 zzhg -> L34
    L16:
        if (r3 >= 5000) goto L18;
        r10 = new zzhg(zzhf.zzb, zzhe.zzm, null, null, 12, null);     // Catch: Exception -> L31 zzhg -> L34
    L18:
        if (r10 != null) goto L30;
        zzkn r06 = this.zzb.zza();     // Catch: Exception -> L31 zzhg -> L34
        String r5 = r9.zza().zzb();     // Catch: Exception -> L31 zzhg -> L34
        this.zze = r9;     // Catch: Exception -> L31 zzhg -> L34
        this.zza = 1;     // Catch: Exception -> L31 zzhg -> L34
        r05 = r06.zzc(r5, r2, r3, null, this);     // Catch: Exception -> L31 zzhg -> L34
        if (r05 == r7) goto L29;
    L30:
        throw r10;     // Catch: Exception -> L31 zzhg -> L34
    L14:
        r10 = null;
    L34:
        e = move-exception;
        throw e;
    L31:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzq, e.getMessage(), null, 8, null);
    }
}
