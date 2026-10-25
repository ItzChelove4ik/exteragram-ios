package com.google.android.recaptcha.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;

/* loaded from: classes5.dex */
final class zzdu extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzdv zzb;
    final /* synthetic */ zzalg zzc;
    private /* synthetic */ Object zzd;

    public zzdu(zzdv r1, long r2, zzalg r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzalg r4 = this.zzc;
        zzdu r0 = new zzdu(this.zzb, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, r4, r8);
        r0.zzd = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdu) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r9);
        if (r1 == 0) goto L5;
        return r9;
    L5:
        zzlp r5 = (zzlp) this.zzd;
        zzdv r4 = this.zzb;
        CoroutineContext r92 = zzdv.zzc(r4).zzc().getCoroutineContext();
        zzdt r12 = new zzdt(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, r4, r5, this.zzc, null);
        this.zza = 1;
        Object r8 = BuildersKt.withContext(r92, r12, this);
        if (r8 != r0) goto L8;
        return r0;
    L8:
        return r8;
    }
}
