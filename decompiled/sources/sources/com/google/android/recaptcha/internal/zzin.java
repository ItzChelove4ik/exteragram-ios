package com.google.android.recaptcha.internal;

import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
final class zzin extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzalc zzc;
    final /* synthetic */ zzio zzd;
    final /* synthetic */ zzaka zze;
    final /* synthetic */ zzdp zzf;
    final /* synthetic */ String zzg;
    final /* synthetic */ zzakg zzh;
    private /* synthetic */ Object zzi;

    public zzin(zzalc r1, zzio r2, zzaka r3, zzdp r4, String r5, zzakg r6, Continuation r7) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzf = r4;
        this.zzg = r5;
        this.zzh = r6;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        zzin r0 = new zzin(this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, r10);
        r0.zzi = r9;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzin) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L18;
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L12;
        if (r1 == 3) goto L9;
        ResultKt.throwOnFailure(r12);     // Catch: Exception -> L54 zzhg -> L57
    L53:
        return Unit.INSTANCE;
    L9:
        zzlp r13 = (zzlp) this.zzi;
        ResultKt.throwOnFailure(r12);     // Catch: Exception -> L54 zzhg -> L57
    L49:
        this.zzi = null;     // Catch: Exception -> L54 zzhg -> L57
        this.zzb = 4;     // Catch: Exception -> L54 zzhg -> L57
        if (((zzmc) r12).zza(r13.zza(), this) != r0) goto L53;
    L51:
        return r0;
    L12:
        r13 = (zzlp) this.zzi;
        ResultKt.throwOnFailure(r12);     // Catch: Exception -> L54 zzhg -> L57
    L37:
        zzaka r122 = (zzaka) r12;     // Catch: Exception -> L54 zzhg -> L57
        if (r122 == null) goto L40;
        String r3 = r122.zza();     // Catch: Exception -> L54 zzhg -> L57
    L41:
        zzaka r4 = this.zze;     // Catch: Exception -> L54 zzhg -> L57
        if (r4 == null) goto L44;
        String r42 = r4.zza();     // Catch: Exception -> L54 zzhg -> L57
    L46:
        if (Intrinsics.areEqual(r3, r42) == true) goto L53;
        zzdp r32 = this.zzf;     // Catch: Exception -> L54 zzhg -> L57
        String r43 = this.zzg;     // Catch: Exception -> L54 zzhg -> L57
        String r6 = this.zzh.zzb();     // Catch: Exception -> L54 zzhg -> L57
        this.zzi = r13;     // Catch: Exception -> L54 zzhg -> L57
        this.zzb = 3;     // Catch: Exception -> L54 zzhg -> L57
        r12 = r32.zzc(r43, r6, r122, this);     // Catch: Exception -> L54 zzhg -> L57
        if (r12 == r0) goto L51;
    L44:
        r42 = null;
        goto L46
    L40:
        r3 = null;
        goto L41
    L15:
        zzlp r14 = (zzlp) this.zza;
        zzlp r44 = (zzlp) this.zzi;
        ResultKt.throwOnFailure(r12);     // Catch: Exception -> L54 zzhg -> L57
    L34:
        this.zzi = r44;     // Catch: Exception -> L54 zzhg -> L57
        this.zza = null;     // Catch: Exception -> L54 zzhg -> L57
        this.zzb = 2;     // Catch: Exception -> L54 zzhg -> L57
        r12 = ((zzmc) r12).zza(r14.zza(), this);     // Catch: Exception -> L54 zzhg -> L57
        if (r12 == r0) goto L51;
        r13 = r44;
        goto L37
    L18:
        ResultKt.throwOnFailure(r12);
        r14 = (zzlp) this.zzi;
        zzalc r123 = this.zzc;     // Catch: Exception -> L54 zzhg -> L57
        zzaer r62 = r123.zzc();     // Catch: Exception -> L54 zzhg -> L57
        zzaer r7 = r123.zzd();     // Catch: Exception -> L54 zzhg -> L57
        Iterator r124 = r123.zzb().iterator();     // Catch: Exception -> L54 zzhg -> L57
    L21:
        if (r124.hasNext() == false) goto L25;
        Object r8 = r124.next();     // Catch: Exception -> L54 zzhg -> L57
        if (Intrinsics.areEqual(((zzale) r8).zza(), "_GRECAPTCHA_KC") == false) goto L21;
    L26:
        zzale r82 = (zzale) r8;     // Catch: Exception -> L54 zzhg -> L57
        if (r82 == null) goto L29;
        String r125 = r82.zzb();     // Catch: Exception -> L54 zzhg -> L57
    L30:
        if (r125 == null) goto L53;
        zzon r83 = zzio.zza(this.zzd);     // Catch: Exception -> L54 zzhg -> L57
        this.zzi = r14;     // Catch: Exception -> L54 zzhg -> L57
        this.zza = r14;     // Catch: Exception -> L54 zzhg -> L57
        this.zzb = 1;     // Catch: Exception -> L54 zzhg -> L57
        r12 = r83.zze(r62, r7, r125, this);     // Catch: Exception -> L54 zzhg -> L57
        if (r12 == r0) goto L51;
        r44 = r14;
        goto L34
    L29:
        r125 = null;
        goto L30
    L25:
        r8 = null;
    L57:
        e = move-exception;
        throw e;
    L54:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzI, e.getMessage(), null, 8, null);
    }
}
