package com.google.android.recaptcha.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzgx extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzgy zzb;
    private /* synthetic */ Object zzc;

    public zzgx(zzgy r1, Continuation r2) {
        this.zzb = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzgx r0 = new zzgx(this.zzb, r3);
        r0.zzc = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgx) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L6;
        ResultKt.throwOnFailure(r14);     // Catch: Exception -> L12
    L10:
        return (String) r14;
    L6:
        ResultKt.throwOnFailure(r14);
        zzlp r142 = (zzlp) this.zzc;
        zzhp r1 = zzhp.zza;     // Catch: Exception -> L12
        zzgy r3 = this.zzb;     // Catch: Exception -> L12
        zzgu r2 = new zzgu(r3);     // Catch: Exception -> L12
        zzgw r9 = new zzgw(r142, r3, null);     // Catch: Exception -> L12
        this.zza = 1;     // Catch: Exception -> L12
        r14 = zzhp.zzd(r1, r2, 0, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, 0.0d, r9, this, 10, null);     // Catch: Exception -> L12
        if (r14 != r0) goto L10;
        return r0;
    L12:
        e = move-exception;
        if ((e instanceof zzhg) == false) goto L17;
        throw e;
    L17:
        throw new zzhg(zzhf.zzb, zzhe.zzao, e.getMessage(), e);
    }
}
