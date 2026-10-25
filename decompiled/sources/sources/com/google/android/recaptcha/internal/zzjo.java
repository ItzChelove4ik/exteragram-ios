package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzjo extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzjt zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zziw zze;
    final /* synthetic */ zzmd zzf;
    private /* synthetic */ Object zzg;

    public zzjo(zzjt r1, String r2, long r3, zziw r5, zzmd r6, Continuation r7) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r5;
        this.zzf = r6;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        zzjo r0 = new zzjo(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, r10);
        r0.zzg = r9;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzjo) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zza == 0) goto L6;
        ResultKt.throwOnFailure(r11);     // Catch: Exception -> L12 zzhg -> L15
    L10:
        return (zzjj) r11;
    L6:
        ResultKt.throwOnFailure(r11);
        zzmg r3 = (zzmg) this.zzg;
        zzjn r1 = new zzjn(this.zzb, r3, this.zzc, this.zzd, this.zze, this.zzf, null);     // Catch: Exception -> L12 zzhg -> L15
        this.zza = 1;     // Catch: Exception -> L12 zzhg -> L15
        r11 = r3.zze(6, null, r1, this);     // Catch: Exception -> L12 zzhg -> L15
        if (r11 != r0) goto L10;
        return r0;
    L15:
        e = move-exception;
        throw e.zzd();
    L12:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zza, e.getMessage(), null, 8, null).zzd();
    }
}
