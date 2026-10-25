package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzgb extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzgo zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzakg zzd;
    private /* synthetic */ Object zze;

    public zzgb(zzgo r1, String r2, zzakg r3, Continuation r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzgb r0 = new zzgb(this.zzb, this.zzc, this.zzd, r5);
        r0.zze = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzgb) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.recaptcha.internal.zzmg] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.google.android.recaptcha.internal.zzmg, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.google.android.recaptcha.internal.zzmg] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r7);
        return r7;
    L7:
        ?? r12 = (zzmg) this.zze;
        ResultKt.throwOnFailure(r7);     // Catch: Exception -> L15
        r1 = r12;
    L13:
        zzalm r72 = (zzalm) r7;     // Catch: Exception -> L15
        ?? r13 = r1;
    L17:
        if (r72.zza().zzr() == true) goto L19;
        return r72;
    L19:
        zzfz r2 = new zzfz(this.zzd, null);
        this.zze = null;
        this.zza = 2;
        Object r6 = r13.zze(86, null, r2, this);
        if (r6 != r0) goto L22;
    L21:
        return r0;
    L22:
        return r6;
    L10:
        ResultKt.throwOnFailure(r7);
        ?? r14 = (zzmg) this.zze;
        zzgo r73 = this.zzb;
        zzalj r4 = zzalk.zza();
        r4.zza(zzgp.zzb());
        r4.zzb("18.9.2");
        r4.zzc(this.zzc);
        r4.zzd(this.zzd.zzb());
        zzga r5 = new zzga(r73, (zzalk) r4.zzp(), null);     // Catch: Exception -> L15
        this.zze = r14;     // Catch: Exception -> L15
        this.zza = 1;     // Catch: Exception -> L15
        r7 = r14.zze(77, null, r5, this);     // Catch: Exception -> L15
        r1 = r14;
        if (r7 == r0) goto L21;
    L15:
        r72 = zzalm.zzd();
        r13 = r1;
        goto L17
    }
}
