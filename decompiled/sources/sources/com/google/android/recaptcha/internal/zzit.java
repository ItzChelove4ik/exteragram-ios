package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzit extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzmg zzc;
    final /* synthetic */ zziv zzd;
    final /* synthetic */ zzdp zze;
    final /* synthetic */ zzakg zzf;
    final /* synthetic */ long zzg;

    public zzit(zzmg r1, zziv r2, zzdp r3, zzakg r4, long r5, Continuation r7) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzg = r5;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        return new zzit(this.zzc, this.zzd, this.zze, this.zzf, this.zzg, r10);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzit) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        ResultKt.throwOnFailure(r11);
    L13:
        return Unit.INSTANCE;
    L6:
        zzmg r12 = (zzmg) this.zza;
        ResultKt.throwOnFailure(r11);
    L9:
        this.zza = null;
        this.zzb = 2;
        if (((zzmc) r11).zza(r12, this) != r0) goto L13;
    L14:
        return r0;
    L7:
        ResultKt.throwOnFailure(r11);
        r12 = this.zzc;
        zziv r8 = this.zzd;
        zzdp r4 = this.zze;
        zzakg r7 = this.zzf;
        long r5 = this.zzg;
        this.zza = r12;
        this.zzb = 1;
        r11 = new zzmc(new zzis(r4, r5, r7, r8, null));
        if (r11 == r0) goto L14;
        goto L9
    }
}
