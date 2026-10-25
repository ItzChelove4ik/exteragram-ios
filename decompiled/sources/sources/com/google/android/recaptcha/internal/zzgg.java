package com.google.android.recaptcha.internal;

import java.util.List;
import java.util.Optional;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
final class zzgg extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzmg zzc;
    final /* synthetic */ zzgo zzd;
    final /* synthetic */ zzalm zze;

    public zzgg(zzmg r1, zzgo r2, zzalm r3, Continuation r4) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzgg(this.zzc, this.zzd, this.zze, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgg) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L12;
        if (r1 == 1) goto L10;
        if (r1 == 2) goto L8;
        ResultKt.throwOnFailure(r8);     // Catch: Exception -> L25 zzhg -> L28
    L21:
        ResultKt.throwOnFailure(((Result) r8).m2027unboximpl());     // Catch: Exception -> L25 zzhg -> L28
    L23:
        return Unit.INSTANCE;
    L8:
        zzgo r12 = (zzgo) this.zza;
        ResultKt.throwOnFailure(r8);     // Catch: Exception -> L25 zzhg -> L28
    L18:
        r12.zza = (List) r8;     // Catch: Exception -> L25 zzhg -> L28
        zzgo r82 = this.zzd;     // Catch: Exception -> L25 zzhg -> L28
        zzgo.zzu(r82, this.zzc.zza());     // Catch: Exception -> L25 zzhg -> L28
        Deferred r83 = zzgo.zzv(r82, Boxing.boxLong(((Number) r82.zzh().get(0)).longValue()), Optional.of(zzgo.zzw(r82)));     // Catch: Exception -> L25 zzhg -> L28
        this.zza = null;     // Catch: Exception -> L25 zzhg -> L28
        this.zzb = 3;     // Catch: Exception -> L25 zzhg -> L28
        r8 = r83.await(this);     // Catch: Exception -> L25 zzhg -> L28
        if (r8 != r0) goto L21;
    L24:
        return r0;
    L10:
        ResultKt.throwOnFailure(r8);     // Catch: Exception -> L25 zzhg -> L28
    L15:
        r12 = this.zzd;     // Catch: Exception -> L25 zzhg -> L28
        zzgo.zzs(r12, this.zze);     // Catch: Exception -> L25 zzhg -> L28
        this.zza = r12;     // Catch: Exception -> L25 zzhg -> L28
        this.zzb = 2;     // Catch: Exception -> L25 zzhg -> L28
        r8 = zzgo.zzt(r12, this);     // Catch: Exception -> L25 zzhg -> L28
        if (r8 != r0) goto L18;
    L12:
        ResultKt.throwOnFailure(r8);
        zzmg r84 = this.zzc;     // Catch: Exception -> L25 zzhg -> L28
        zzgf r13 = new zzgf(this.zzd, this.zze, null);     // Catch: Exception -> L25 zzhg -> L28
        this.zzb = 1;     // Catch: Exception -> L25 zzhg -> L28
        if (r84.zze(50, null, r13, this) == r0) goto L24;
    L28:
        e = move-exception;
        throw e;
    L25:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzak, e.getMessage(), null, 8, null);
    }
}
