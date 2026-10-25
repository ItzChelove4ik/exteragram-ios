package com.google.android.recaptcha.internal;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzej extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzmg zzc;
    final /* synthetic */ zzev zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ zzaka zzf;
    final /* synthetic */ List zzg;

    public zzej(zzmg r1, zzev r2, String r3, zzaka r4, List r5, Continuation r6) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzg = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        return new zzej(this.zzc, this.zzd, this.zze, this.zzf, this.zzg, r9);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzej) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r6);
    L12:
        this.zzg.add((zzex) r6);
        return Unit.INSTANCE;
    L6:
        zzmg r12 = (zzmg) this.zza;
        ResultKt.throwOnFailure(r6);
    L9:
        this.zza = null;
        this.zzb = 2;
        r6 = ((zzmc) r6).zza(r12, this);
        if (r6 != r0) goto L12;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r6);
        r12 = this.zzc;
        zzev r62 = this.zzd;
        String r3 = this.zze;
        zzaka r4 = this.zzf;
        this.zza = r12;
        this.zzb = 1;
        r6 = r62.zzh(r3, r4, this);
        if (r6 == r0) goto L14;
        goto L9
    }
}
