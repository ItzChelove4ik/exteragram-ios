package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;

/* loaded from: classes5.dex */
final class zzka extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzke zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ CompletableDeferred zze;
    private /* synthetic */ Object zzf;

    public zzka(zzke r1, long r2, CompletableDeferred r4, Continuation r5) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzka r0 = new zzka(this.zzc, this.zzd, this.zze, r8);
        r0.zzf = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzka) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 != 0) goto L33;
        ResultKt.throwOnFailure(r10);
        zzlp r12 = (zzlp) this.zzf;
        zzke r102 = this.zzc;     // Catch: zzhg -> L29
        zziz r3 = zzke.zzn(r102);     // Catch: zzhg -> L29
        long r4 = this.zzd;     // Catch: zzhg -> L29
        this.zzf = r102;     // Catch: zzhg -> L29
        this.zza = r12;     // Catch: zzhg -> L29
        this.zzb = 1;     // Catch: zzhg -> L29
        zzka r8 = this;
        Object r9 = r3.zza(r4, r4, r8);     // Catch: zzhg -> L27
        if (r9 == r0) goto L22;
        zzke r2 = r102;
        r10 = r9;
    L20:
        r8.zzf = r2;     // Catch: zzhg -> L27
        r8.zza = null;     // Catch: zzhg -> L27
        r8.zzb = 2;     // Catch: zzhg -> L27
        r10 = ((zzmc) r10).zza(r12.zza(), r8);     // Catch: zzhg -> L27
        if (r10 == r0) goto L22;
        zzke r02 = r2;
    L24:
        zzke.zzi(r02, (zzakg) r10);     // Catch: zzhg -> L27
        zzke.zzg(r8.zzc, zzkm.zzb());     // Catch: zzhg -> L27
        return Boxing.boxBoolean(r8.zze.complete(Unit.INSTANCE));
    L22:
        return r0;
    L27:
        zzhg e = e;
    L28:
        zzhg r103 = e;
    L31:
        zzke.zzj(r8.zzc, r103);
        throw r103;
    L29:
        e = e;
        r8 = this;
        goto L28
    L33:
        if (r1 == 1) goto L10;
        r02 = (zzke) this.zzf;
        ResultKt.throwOnFailure(r10);     // Catch: zzhg -> L8
        r8 = this;
        goto L24
    L10:
        r12 = (zzlp) this.zza;
        r2 = (zzke) this.zzf;
        ResultKt.throwOnFailure(r10);     // Catch: zzhg -> L8
        r8 = this;
    L8:
        e = move-exception;
        r103 = e;
        r8 = this;
        goto L31
    }
}
