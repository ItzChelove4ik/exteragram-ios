package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzof extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzon zzc;
    private /* synthetic */ Object zzd;

    public zzof(zzon r1, Continuation r2) {
        this.zzc = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzof r0 = new zzof(this.zzc, r3);
        r0.zzd = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzof) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L11;
        if (r1 == 1) goto L10;
        if (r1 == 2) goto L9;
        if (r1 == 3) goto L8;
        ResultKt.throwOnFailure(r8);
    L23:
        return Unit.INSTANCE;
    L8:
        zzmg r12 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r8);
    L19:
        this.zzd = null;
        this.zzb = 4;
        if (((zzmc) r8).zza(r12, this) != r0) goto L23;
    L24:
        return r0;
    L9:
        r12 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r8);
    L17:
        zznm r82 = this.zzc.zzc();
        String r3 = r12.zza().zza();
        this.zzd = r12;
        this.zzb = 3;
        r8 = new zzmc(new zznl(r82, r3, null));
        if (r8 == r0) goto L24;
    L10:
        zzmg r13 = (zzmg) this.zza;
        zzmg r4 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r8);
    L14:
        this.zzd = r4;
        this.zza = null;
        this.zzb = 2;
        if (((zzmc) r8).zza(r13, this) == r0) goto L24;
        r12 = r4;
        goto L17
    L11:
        ResultKt.throwOnFailure(r8);
        r13 = (zzmg) this.zzd;
        zzni r83 = this.zzc.zza();
        String r6 = r13.zza().zza();
        this.zzd = r13;
        this.zza = r13;
        this.zzb = 1;
        r8 = r83.zzb(r6, this);
        if (r8 == r0) goto L24;
        r4 = r13;
        goto L14
    }
}
