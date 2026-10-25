package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzga extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzgo zzb;
    final /* synthetic */ zzalk zzc;

    public zzga(zzgo r1, zzalk r2, Continuation r3) {
        this.zzb = r1;
        this.zzc = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzga(this.zzb, this.zzc, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzga) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r8);
        if (r1 != 0) goto L8;
        zzgo r82 = this.zzb;
        zzmv r12 = zzgo.zzo(r82);
        String r83 = zzgo.zzp(r82).zzb();
        zzalk r2 = this.zzc;
        this.zza = 1;
        r8 = r12.zza(r83, r2, this);
        if (r8 != r0) goto L8;
        return r0;
    L8:
        zzalm r84 = (zzalm) r8;
        if (r84.zza().zzr() == true) goto L12;
        return r84;
    L12:
        throw new zzhg(zzhf.zzb, zzhe.zzah, null, null, 12, null);
    }
}
