package com.google.android.recaptcha.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzdt extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzdv zzc;
    final /* synthetic */ zzlp zzd;
    final /* synthetic */ zzalg zze;

    public zzdt(long r1, zzdv r3, zzlp r4, zzalg r5, Continuation r6) {
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        return new zzdt(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, this.zzc, this.zzd, this.zze, r9);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzdt) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r17) {
        Object r14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zzb == 0) goto L9;
        Ref.ObjectRef r1 = (Ref.ObjectRef) this.zza;
        ResultKt.throwOnFailure(r17);     // Catch: Exception -> L7 Throwable -> L22
        Object r0 = r17;
    L15:
        return (zzali) r0;
    L7:
        e = e;
    L25:
        e.getMessage();
        zzhg r12 = (zzhg) r1.element;
        if (r12 == null) goto L28;
        throw r12;
    L28:
        throw zzdv.zza(this.zzc, e);
    L9:
        ResultKt.throwOnFailure(r17);
        Ref.ObjectRef r6 = new Ref.ObjectRef();
        zzhp r02 = zzhp.zza;     // Catch: Exception -> L20 Throwable -> L22
        zzds r2 = new zzds(this.zzd, this.zzc, this.zze, r6, null);     // Catch: Exception -> L20 Throwable -> L22
        Ref.ObjectRef r15 = r6;
        this.zza = r15;     // Catch: Exception -> L18 Throwable -> L22
        this.zzb = 1;     // Catch: Exception -> L18 Throwable -> L22
        r0 = zzhp.zze(r02, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, 0, 0, 0, 0.0d, r2, this, 30, null);     // Catch: Exception -> L18 Throwable -> L22
        if (r0 == r14) goto L17;
        r1 = r15;
        goto L15
    L17:
        return r14;
    L18:
        e = e;
    L24:
        r1 = r15;
    L20:
        e = e;
        r15 = r6;
    L22:
        th = move-exception;
        throw th;
    }
}
