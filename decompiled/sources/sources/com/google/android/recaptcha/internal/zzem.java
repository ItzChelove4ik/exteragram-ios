package com.google.android.recaptcha.internal;

import java.util.Iterator;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzem extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    int zzd;
    final /* synthetic */ zzakg zze;
    final /* synthetic */ zzeo zzf;
    final /* synthetic */ zzmg zzg;

    public zzem(zzakg r1, zzeo r2, zzmg r3, Continuation r4) {
        this.zze = r1;
        this.zzf = r2;
        this.zzg = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzem(this.zze, this.zzf, this.zzg, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzem) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00be -> B:16:0x008c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzd;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        Iterator r12 = (Iterator) this.zzb;
        zzakk r3 = (zzakk) this.zza;
        ResultKt.throwOnFailure(r9);
    L17:
        if (r12.hasNext() == false) goto L25;
        zzev r92 = (zzev) r12.next();
        zzmg r4 = this.zzg;
        this.zza = r3;
        this.zzb = r12;
        this.zzc = r4;
        this.zzd = 1;
        r9 = r92.zzg(r3, this);
        if (r9 == r0) goto L24;
        zzakk r7 = r3;
        Iterator r32 = r12;
        zzmg r13 = r4;
        zzakk r42 = r7;
    L21:
        this.zza = r42;
        this.zzb = r32;
        this.zzc = null;
        this.zzd = 2;
        if (r13.zzc((zzmb) r9, this) == r0) goto L24;
        r12 = r32;
        r3 = r42;
    L24:
        return r0;
    L25:
        Result.Companion r8 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(Unit.INSTANCE));
    L6:
        r13 = (zzmg) this.zzc;
        r32 = (Iterator) this.zzb;
        r42 = (zzakk) this.zza;
        ResultKt.throwOnFailure(r9);
        goto L21
    L7:
        ResultKt.throwOnFailure(r9);
        zzakg r93 = this.zze;
        if (r93.zzc() == true) goto L11;
        Result.Companion r82 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(ResultKt.createFailure(new zzhg(zzhf.zzb, zzhe.zzu, null, null, 12, null))));
    L11:
        r3 = r93.zzd();
        if (r3.zza().zzr() == false) goto L15;
        Result.Companion r83 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(ResultKt.createFailure(new zzhg(zzhf.zzb, zzhe.zzu, null, null, 12, null))));
    L15:
        zzeo r94 = this.zzf;
        zzeo.zzi(r94, r3.zza());
        r12 = zzeo.zzj(r94).iterator();
        goto L17
    }
}
