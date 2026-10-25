package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zznv extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzoa zzb;
    private /* synthetic */ Object zzc;

    public zznv(zzoa r1, Continuation r2) {
        this.zzb = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zznv r0 = new zznv(this.zzb, r3);
        r0.zzc = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zznv) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r7);     // Catch: Exception -> L19
    L16:
        return (zzagx) r7;
    L7:
        zzmg r12 = (zzmg) this.zzc;
        ResultKt.throwOnFailure(r7);     // Catch: Exception -> L19
    L13:
        this.zzc = null;     // Catch: Exception -> L19
        this.zza = 2;     // Catch: Exception -> L19
        r7 = ((zzmb) r7).zza(r12, this);     // Catch: Exception -> L19
        if (r7 != r0) goto L16;
    L18:
        return r0;
    L10:
        ResultKt.throwOnFailure(r7);
        r12 = (zzmg) this.zzc;
        zzoa r72 = this.zzb;     // Catch: Exception -> L19
        int r4 = zzoa.zzl(r72);     // Catch: Exception -> L19
        zznu r5 = new zznu(r72, null);     // Catch: Exception -> L19
        this.zzc = r12;     // Catch: Exception -> L19
        this.zza = 1;     // Catch: Exception -> L19
        r7 = new zzmb(r4, r5, null);     // Catch: Exception -> L19
        if (r7 == r0) goto L18;
    L19:
        return null;
    }
}
