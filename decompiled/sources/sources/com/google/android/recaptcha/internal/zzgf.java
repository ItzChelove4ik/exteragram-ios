package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzgf extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzgo zzc;
    final /* synthetic */ zzalm zzd;

    public zzgf(zzgo r1, zzalm r2, Continuation r3) {
        this.zzc = r1;
        this.zzd = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzgf(this.zzc, this.zzd, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgf) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object, kotlin.Unit] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.zzb;
        if (r0 == 0) goto L12;
        if (r0 == 1) goto L11;
        if (r0 != 2) goto L10;
        ResultKt.throwOnFailure(r8);     // Catch: zzhg -> L8
    L19:
        zzgf this = Unit.INSTANCE;
        return this;
    L10:
        zzhg r7 = (zzhg) this.zza;
        ResultKt.throwOnFailure(r8);
        throw r7;
    L11:
        ResultKt.throwOnFailure(r8);
    L14:
        String r82 = (String) r8;
        if (r82 == null) goto L27;
        zzgz r02 = zzgo.zzr(this.zzc);     // Catch: zzhg -> L8
        zzalm r3 = this.zzd;     // Catch: zzhg -> L8
        this.zzb = 2;     // Catch: zzhg -> L8
        if (r02.zza(r3, r82, this) != r1) goto L19;
    L28:
        return r1;
    L27:
        throw new zzhg(zzhf.zzb, zzhe.zzaq, null, null, 12, null);
    L12:
        ResultKt.throwOnFailure(r8);
        zzgy r83 = zzgo.zzB(this.zzc);
        this.zzb = 1;
        r8 = r83.zzc(this);
        if (r8 == r1) goto L28;
    L8:
        e = move-exception;
        zzgy r03 = zzgo.zzB(this.zzc);
        this.zza = e;
        this.zzb = 3;
        if (new zzmc(new zzgr(r03, null)) == r1) goto L28;
        throw e;
    }
}
