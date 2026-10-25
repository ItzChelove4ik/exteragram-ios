package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
final class zzie extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzif zzc;
    private /* synthetic */ Object zzd;

    public zzie(zzif r1, Continuation r2) {
        this.zzc = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        zzie r0 = new zzie(this.zzc, r3);
        r0.zzd = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzie) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L7;
        if (r1 == 1) goto L6;
        zzmg r02 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r6);
    L14:
        zzaka r62 = (zzaka) r6;
        if (r62 == null) goto L18;
        String r63 = r62.zza();
        if (r63 == null) goto L18;
    L19:
        zzame r12 = zzamf.zza();
        r12.zza(r02.zza().zza());
        zzif r5 = this.zzc;
        r12.zzb(zzif.zzd(r5).getPackageName());
        r12.zzv(zzif.zze(r5).zzd());
        r12.zzc(zzif.zza());
        r12.zzd(r02.zza().zzb());
        r12.zze(String.valueOf(zzif.zzb()));
        r12.zzf(r63);
        r12.zzg(zzif.zze(r5).zza());
        r12.zzh(zzif.zze(r5).zzb());
        r12.zzt(zzif.zze(r5).zzc());
        r12.zzu(zzgp.zzb());
        return r12.zzp();
    L18:
        r63 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L19
    L6:
        zzmg r13 = (zzmg) this.zza;
        zzmg r2 = (zzmg) this.zzd;
        ResultKt.throwOnFailure(r6);
        zzmg r22 = r13;
        zzmg r14 = r2;
    L10:
        this.zzd = r14;
        this.zza = null;
        this.zzb = 2;
        r6 = ((zzmc) r6).zza(r22, this);
        if (r6 == r0) goto L21;
        r02 = r14;
    L21:
        return r0;
    L7:
        ResultKt.throwOnFailure(r6);
        r14 = (zzmg) this.zzd;
        zzon r64 = zzif.zzc(this.zzc);
        this.zzd = r14;
        this.zza = r14;
        this.zzb = 1;
        r6 = r64.zzg(this);
        if (r6 == r0) goto L21;
        r22 = r14;
        goto L10
    }
}
