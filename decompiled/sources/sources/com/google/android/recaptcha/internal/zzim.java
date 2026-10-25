package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzim extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    double zzc;
    double zzd;
    int zze;
    final /* synthetic */ long zzf;
    final /* synthetic */ zzio zzg;
    final /* synthetic */ zzdp zzh;
    final /* synthetic */ String zzi;
    final /* synthetic */ zzakg zzj;
    final /* synthetic */ String zzk;
    private /* synthetic */ Object zzl;

    public zzim(long r1, zzio r3, zzdp r4, String r5, zzakg r6, String r7, String r8, Continuation r9) {
        this.zzf = r1;
        this.zzg = r3;
        this.zzh = r4;
        this.zzi = r5;
        this.zzj = r6;
        this.zzk = r7;
        super(2, r9);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r11, Continuation r12) {
        zzim r0 = new zzim(this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, null, r12);
        r0.zzl = r11;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzim) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r21) {
        Object r8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(this.zze) {
            case 0: goto L14;
            case 1: goto L13;
            case 2: goto L12;
            case 3: goto L11;
            case 4: goto L10;
            case 5: goto L9;
            case 6: goto L8;
            case 7: goto L7;
            case 8: goto L6;
            default: goto L4;
        };
    L4:
        String r0 = (String) this.zzl;
        ResultKt.throwOnFailure(r21);
        return r0;
    L6:
        zzmg r02 = (zzmg) this.zza;
        String r1 = (String) this.zzl;
        ResultKt.throwOnFailure(r21);
        Object r2 = r21;
    L39:
        this.zzl = r1;
        this.zza = null;
        this.zze = 9;
        if (((zzmb) r2).zza(r02, this) == r8) goto L42;
        return r1;
    L42:
        return r8;
    L7:
        zzalc r03 = (zzalc) this.zzb;
        zzaka r12 = (zzaka) this.zza;
        zzmg r22 = (zzmg) this.zzl;
        ResultKt.throwOnFailure(r21);
        Object r3 = r21;
    L34:
        String r32 = (String) r3;
    L35:
        zzalc r11 = r03;
        zzaka r13 = r12;
        r02 = r22;
        r1 = r32;
        zzio r122 = this.zzg;
        zzdp r14 = this.zzh;
        String r15 = this.zzi;
        zzakg r23 = this.zzj;
        this.zzl = r1;
        this.zza = r02;
        this.zzb = null;
        this.zze = 8;
        r2 = new zzmb(29, new zzin(r11, r122, r13, r14, r15, r23, null), null);
        if (r2 == r8) goto L42;
    L8:
        zzaka r04 = (zzaka) this.zza;
        zzmg r16 = (zzmg) this.zzl;
        ResultKt.throwOnFailure(r21);
        r22 = r16;
        r12 = r04;
        Object r05 = r21;
    L30:
        r03 = (zzalc) r05;
        if (this.zzj.zzl() == false) goto L36;
        zzbe r33 = zzio.zzc(this.zzg);
        this.zzl = r22;
        this.zza = r12;
        this.zzb = r03;
        this.zze = 7;
        r3 = r33.zza(r03, this);
        if (r3 == r8) goto L42;
    L36:
        r32 = r03.zza();
        goto L35
    L9:
        zzmg r06 = (zzmg) this.zzb;
        r12 = (zzaka) this.zza;
        r22 = (zzmg) this.zzl;
        ResultKt.throwOnFailure(r21);
        zzmg r10 = r06;
        Object r07 = r21;
    L28:
        this.zzl = r22;
        this.zza = r12;
        this.zzb = null;
        this.zze = 6;
        r05 = ((zzmc) r07).zza(r10, this);
        if (r05 == r8) goto L42;
    L10:
        double r08 = this.zzc;
        zzaka r24 = (zzaka) this.zza;
        zzmg r34 = (zzmg) this.zzl;
        ResultKt.throwOnFailure(r21);
        double r5 = r08;
        zzaka r112 = r24;
        r10 = r34;
        Object r09 = r21;
    L25:
        zzkp r35 = zzio.zzb(this.zzg);
        this.zzl = r10;
        this.zza = r112;
        this.zzb = r10;
        this.zze = 5;
        r07 = r35.zza(this.zzk, this.zzj, (zzaku) r09, null, (long) r5, this);
        if (r07 == r8) goto L42;
        r22 = r10;
        r12 = r112;
        goto L28
    L11:
        double r010 = this.zzc;
        zzmg r25 = (zzmg) this.zzb;
        zzaka r36 = (zzaka) this.zza;
        zzmg r4 = (zzmg) this.zzl;
        ResultKt.throwOnFailure(r21);
        r5 = r010;
        Object r011 = r21;
    L22:
        this.zzl = r4;
        this.zza = r36;
        this.zzb = null;
        this.zzc = r5;
        this.zze = 4;
        r09 = ((zzmb) r011).zza(r25, this);
        if (r09 == r8) goto L42;
        r112 = r36;
        r10 = r4;
        goto L25
    L12:
        double r012 = this.zzd;
        double r26 = this.zzc;
        zzmg r42 = (zzmg) this.zzl;
        ResultKt.throwOnFailure(r21);
        r5 = r26;
        zzmg r37 = r42;
        Object r27 = r21;
    L19:
        zzdp r113 = this.zzh;
        String r123 = this.zzi;
        zzakg r132 = this.zzj;
        zzaka r142 = (zzaka) r27;
        this.zzl = r37;
        this.zza = r142;
        this.zzb = r37;
        this.zzc = r5;
        this.zze = 3;
        r011 = new zzmb(27, new zzil(r113, r123, r132, r142, (long) r012, null), null);
        if (r011 == r8) goto L42;
        r25 = r37;
        r4 = r25;
        r36 = r142;
        goto L22
    L13:
        r012 = this.zzd;
        double r28 = this.zzc;
        zzmg r43 = (zzmg) this.zza;
        zzmg r52 = (zzmg) this.zzl;
        ResultKt.throwOnFailure(r21);
        r37 = r52;
        r5 = r28;
        Object r29 = r21;
    L17:
        this.zzl = r37;
        this.zza = null;
        this.zzc = r5;
        this.zzd = r012;
        this.zze = 2;
        r27 = ((zzmc) r29).zza(r43, this);
        if (r27 == r8) goto L42;
    L14:
        ResultKt.throwOnFailure(r21);
        r43 = (zzmg) this.zzl;
        long r013 = this.zzf;
        zzon r210 = zzio.zza(this.zzg);
        this.zzl = r43;
        this.zza = r43;
        double r014 = r013;
        r5 = 0.55d * r014;
        this.zzc = r5;
        r012 = r014 * 0.45d;
        this.zzd = r012;
        this.zze = 1;
        r29 = r210.zzg(this);
        if (r29 == r8) goto L42;
        r37 = r43;
        goto L17
    }
}
