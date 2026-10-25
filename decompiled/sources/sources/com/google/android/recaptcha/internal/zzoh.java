package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
final class zzoh extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzon zzd;
    final /* synthetic */ zzmg zze;
    private /* synthetic */ Object zzf;

    public zzoh(zzon r1, zzmg r2, Continuation r3) {
        this.zzd = r1;
        this.zze = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzoh r0 = new zzoh(this.zzd, this.zze, r4);
        r0.zzf = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzoh) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(this.zzc) {
            case 0: goto L19;
            case 1: goto L18;
            case 2: goto L17;
            case 3: goto L16;
            case 4: goto L15;
            case 5: goto L14;
            case 6: goto L13;
            case 7: goto L12;
            case 8: goto L11;
            case 9: goto L10;
            case 10: goto L9;
            case 11: goto L7;
            case 12: goto L6;
            default: goto L4;
        };
    L4:
        ResultKt.throwOnFailure(r10);
        return r10;
    L6:
        zzaks r1 = (zzaks) this.zza;
        zzaki r2 = (zzaki) this.zzf;
        ResultKt.throwOnFailure(r10);
    L67:
        zzaka r102 = (zzaka) r10;
        if (r102 == null) goto L74;
        zzakg r22 = r2.zza();
        this.zzf = null;
        this.zza = null;
        this.zzc = 13;
        zzakf r9 = (zzakf) r22.zzG();
        zzakd r3 = (zzakd) r22.zzg().zzG();
        r3.zza(r102.zze());
        r9.zzb((zzake) r3.zzp());
        zzakj r23 = (zzakj) r22.zzd().zzG();
        r23.zza(r102.zza());
        r23.zzc(r102.zzb());
        r23.zze(r102.zzc());
        r23.zzg(r102.zzd());
        r9.zza((zzakk) r23.zzp());
        r9.zzc(r1.zza());
        zzafv r92 = r9.zzp();
        if (r92 == r0) goto L90;
        return r92;
    L90:
        return r0;
    L74:
        throw new zzhg(zzhf.zzb, zzhe.zzaA, null, null, 12, null);
    L7:
        zzlp r12 = (zzlp) this.zzb;
        zzaks r24 = (zzaks) this.zza;
        zzaki r4 = (zzaki) this.zzf;
        ResultKt.throwOnFailure(r10);
        zzaki r8 = r4;
        zzlp r42 = r12;
        r1 = r24;
    L8:
        r2 = r8;
        this.zzf = r2;
        this.zza = r1;
        this.zzb = null;
        this.zzc = 12;
        r10 = ((zzmc) r10).zza(r42.zza(), this);
        if (r10 == r0) goto L90;
    L9:
        ResultKt.throwOnFailure(r10);
    L82:
        throw new zzhg(zzhf.zzb, zzhe.zzaz, null, null, 12, null);
    L10:
        zzlp r13 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r10);
    L78:
        this.zzf = null;
        this.zzc = 10;
        if (((zzmc) r10).zza(r13.zza(), this) != r0) goto L82;
    L11:
        zzaki r14 = (zzaki) this.zza;
        r42 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r10);
    L53:
        zzaks r103 = (zzaks) r10;
        if (r103 == null) goto L56;
        String r5 = r103.zzb();
    L58:
        if (Intrinsics.areEqual(r5, "18.9.2") == true) goto L60;
    L75:
        zzon r104 = this.zzd;
        this.zzf = r42;
        this.zza = null;
        this.zzc = 9;
        r10 = r104.zzh(this);
        if (r10 == r0) goto L90;
        r13 = r42;
        goto L78
    L60:
        if (Intrinsics.areEqual(r103.zzc(), r14.zzc()) == false) goto L75;
        zzng r25 = this.zzd.zzb();
        this.zzf = r14;
        this.zza = r103;
        this.zzb = r42;
        this.zzc = 11;
        Object r26 = zzoa.zzi(r25, this);
        if (r26 == r0) goto L90;
        r8 = r14;
        r1 = r103;
        r10 = r26;
        goto L8
    L56:
        r5 = null;
        goto L58
    L12:
        zzlp r15 = (zzlp) this.zzb;
        zzaki r43 = (zzaki) this.zza;
        zzlp r52 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r10);
        zzlp r44 = r15;
        r14 = r43;
    L50:
        this.zzf = r52;
        this.zza = r14;
        this.zzb = null;
        this.zzc = 8;
        r10 = ((zzmc) r10).zza(r44.zza(), this);
        if (r10 == r0) goto L90;
        r42 = r52;
        goto L53
    L13:
        ResultKt.throwOnFailure(r10);
    L89:
        throw new zzhg(zzhf.zzb, zzhe.zzay, null, null, 12, null);
    L14:
        zzlp r16 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r10);
    L85:
        this.zzf = null;
        this.zzc = 6;
        if (((zzmc) r10).zza(r16.zza(), this) != r0) goto L89;
    L15:
        ResultKt.throwOnFailure(r10);
    L37:
        throw new zzhg(zzhf.zzb, zzhe.zzax, null, null, 12, null);
    L16:
        r16 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r10);
    L33:
        this.zzf = null;
        this.zzc = 4;
        if (((zzmc) r10).zza(r16.zza(), this) != r0) goto L37;
    L17:
        r16 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r10);
    L25:
        zzaki r105 = (zzaki) r10;
        if (r105 == null) goto L28;
        String r45 = r105.zzb();
    L30:
        if (Intrinsics.areEqual(r45, "18.9.2") == true) goto L38;
        zzon r106 = this.zzd;
        this.zzf = r16;
        this.zzc = 3;
        r10 = r106.zzh(this);
        if (r10 == r0) goto L90;
    L38:
        if (r105 == null) goto L42;
        zzakg r46 = r105.zza();
        if (r46 == null) goto L42;
        Long r47 = Boxing.boxLong(r46.zzj());
    L43:
        if (r47 != null) goto L45;
    L83:
        zzon r107 = this.zzd;
        zzmg r27 = this.zze;
        zzni r108 = r107.zza();
        String r28 = r27.zza().zza();
        this.zzf = r16;
        this.zzc = 5;
        r10 = r108.zzb(r28, this);
        if (r10 == r0) goto L90;
    L45:
        if (r47.longValue() < System.currentTimeMillis()) goto L83;
        zznm r48 = this.zzd.zzc();
        this.zzf = r16;
        this.zza = r105;
        this.zzb = r16;
        this.zzc = 7;
        Object r49 = zzoa.zzi(r48, this);
        if (r49 == r0) goto L90;
        r52 = r16;
        r14 = r105;
        r10 = r49;
        r44 = r52;
    L42:
        r47 = null;
        goto L43
    L28:
        r45 = null;
        goto L30
    L18:
        zzlp r17 = (zzlp) this.zza;
        zzlp r410 = (zzlp) this.zzf;
        ResultKt.throwOnFailure(r10);
    L22:
        this.zzf = r410;
        this.zza = null;
        this.zzc = 2;
        r10 = ((zzmc) r10).zza(r17.zza(), this);
        if (r10 == r0) goto L90;
        r16 = r410;
        goto L25
    L19:
        ResultKt.throwOnFailure(r10);
        r17 = (zzlp) this.zzf;
        zzni r109 = this.zzd.zza();
        this.zzf = r17;
        this.zza = r17;
        this.zzc = 1;
        r10 = zzoa.zzi(r109, this);
        if (r10 == r0) goto L90;
        r410 = r17;
        goto L22
    }
}
