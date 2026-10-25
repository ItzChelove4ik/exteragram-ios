package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.TimeoutCancellationException;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzmb {
    private final Function2 zza;
    private final Integer zzb;
    private final int zzc;

    public zzmb(int r1, Function2 r2, Integer r3) {
        this.zzc = r1;
        this.zza = r2;
        this.zzb = r3;
    }

    public final Object zza(zzmg r9, Continuation r10) {
        if ((r10 instanceof zzma) == false) goto L7;
        zzma r0 = (zzma) r10;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L22;
        if (r2 != 1) goto L20;
        zzlp r8 = r0.zzd;
        ResultKt.throwOnFailure(r102);     // Catch: Exception -> L14 TimeoutCancellationException -> L16 zzhg -> L18 Throwable -> L35
    L26:
        r8.zzb();     // Catch: Exception -> L14 TimeoutCancellationException -> L16 zzhg -> L18 Throwable -> L35
        return r102;
    L18:
        e = move-exception;
        zzhg r92 = e;
    L45:
        r8.zzc(r92);
        throw r92;
    L16:
        e = move-exception;
        TimeoutCancellationException r93 = e;
    L42:
        zzhg r02 = new zzhg(zzhf.zzb, zzhe.zzb, r93.getMessage(), null, 8, null);
        r8.zzc(r02);
        throw r02;
    L14:
        e = move-exception;
        Exception r94 = e;
    L39:
        zzhg r03 = new zzhg(zzhf.zzb, zzhe.zza, r94.getMessage(), null, 8, null);
        r8.zzc(r03);
        throw r03;
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L22:
        ResultKt.throwOnFailure(r102);
        zzlp r95 = r9.zzf(this.zzc, this.zzb);
        Function2 r82 = this.zza;     // Catch: Exception -> L29 TimeoutCancellationException -> L31 zzhg -> L33 Throwable -> L35
        r0.zzd = r95;     // Catch: Exception -> L29 TimeoutCancellationException -> L31 zzhg -> L33 Throwable -> L35
        r0.zzc = 1;     // Catch: Exception -> L29 TimeoutCancellationException -> L31 zzhg -> L33 Throwable -> L35
        r102 = r82.invoke(r95, r0);     // Catch: Exception -> L29 TimeoutCancellationException -> L31 zzhg -> L33 Throwable -> L35
        if (r102 == r12) goto L28;
        r8 = r95;
        goto L26
    L28:
        return r12;
    L33:
        e = move-exception;
        r92 = e;
        r8 = r95;
    L31:
        e = move-exception;
        r93 = e;
        r8 = r95;
    L29:
        e = move-exception;
        r94 = e;
        r8 = r95;
    L35:
        th = move-exception;
        throw th;
    L7:
        r0 = new zzma(this, r10);
        goto L8
    }
}
