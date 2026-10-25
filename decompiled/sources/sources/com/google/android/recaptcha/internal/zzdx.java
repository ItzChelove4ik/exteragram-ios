package com.google.android.recaptcha.internal;

import com.google.android.gms.recaptchabase.InitRequest;
import com.google.android.gms.recaptchabase.RecaptchaBase;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzdx extends SuspendLambda implements Function2 {
    final /* synthetic */ zzdy zza;
    final /* synthetic */ zzakg zzb;

    public zzdx(zzdy r1, zzakg r2, Continuation r3) {
        this.zza = r1;
        this.zzb = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzdx(this.zza, this.zzb, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdx) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r8);
        zzdy r82 = this.zza;
        if (zzdy.zzk(r82).zza() == true) goto L6;
        Result.Companion r7 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(ResultKt.createFailure(new zzhg(zzhf.zzb, zzhe.zzB, null, null, 12, null))));
    L6:
        zzakg r72 = this.zzb;
        if (r72.zze() == true) goto L9;
    L13:
        Result.Companion r73 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(ResultKt.createFailure(new zzhg(zzhf.zzb, zzhe.zzO, null, null, 12, null))));
    L9:
        if (r72.zzg().zza().zzr() == true) goto L13;
        InitRequest r74 = new InitRequest.Builder().build();
        zzdy.zzi(r82, zzic.zza(RecaptchaBase.getClient(zzdy.zzh(r82)).init(r74)));
        Result.Companion r75 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(Unit.INSTANCE));
    }
}
