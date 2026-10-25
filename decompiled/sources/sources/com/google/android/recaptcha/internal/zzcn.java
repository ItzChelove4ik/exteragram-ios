package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzcn extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzmg zzc;
    final /* synthetic */ zzcl zzd;
    final /* synthetic */ String zze;
    final /* synthetic */ zzaka zzf;
    final /* synthetic */ long zzg;

    public zzcn(zzmg r1, zzcl r2, String r3, zzaka r4, long r5, Continuation r7) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzg = r5;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        return new zzcn(this.zzc, this.zzd, this.zze, this.zzf, this.zzg, r10);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzcn) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r11);
        return r11;
    L7:
        zzmg r12 = (zzmg) this.zza;
        ResultKt.throwOnFailure(r11);
    L10:
        this.zza = null;
        this.zzb = 2;
        Object r10 = ((zzmc) r11).zza(r12, this);
        if (r10 == r0) goto L14;
        return r10;
    L14:
        return r0;
    L8:
        ResultKt.throwOnFailure(r11);
        r12 = this.zzc;
        zzcl r4 = this.zzd;
        String r5 = this.zze;
        zzaka r6 = this.zzf;
        long r7 = this.zzg;
        this.zza = r12;
        this.zzb = 1;
        r11 = new zzmc(new zzcg(r4, r5, r6, r7, null));
        if (r11 == r0) goto L14;
        goto L10
    }
}
