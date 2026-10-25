package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzoj extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzon zzd;
    final /* synthetic */ zzakg zze;
    final /* synthetic */ zzmg zzf;
    private /* synthetic */ Object zzg;

    public zzoj(zzon r1, zzakg r2, zzmg r3, Continuation r4) {
        this.zzd = r1;
        this.zze = r2;
        this.zzf = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzoj r0 = new zzoj(this.zzd, this.zze, this.zzf, r5);
        r0.zzg = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzoj) create((zzlp) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(this.zzc) {
            case 0: goto L15;
            case 1: goto L14;
            case 2: goto L13;
            case 3: goto L12;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L8;
            case 7: goto L9;
            case 8: goto L8;
            case 9: goto L7;
            case 10: goto L6;
            case 11: goto L5;
            default: goto L4;
        };
    L4:
        ResultKt.throwOnFailure(r9);
    L53:
        return Unit.INSTANCE;
    L5:
        zzlp r1 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
    L49:
        this.zzg = null;
        this.zzc = 12;
        if (((zzmc) r9).zza(r1.zza(), this) != r0) goto L53;
    L54:
        return r0;
    L6:
        r1 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
    L46:
        if (r9 != null) goto L53;
        zzon r92 = this.zzd;
        zzakg r2 = this.zze;
        this.zzg = r1;
        this.zzc = 11;
        r9 = new zzmc(new zzom(r2, r92, null));
        if (r9 == r0) goto L54;
    L7:
        zzlp r12 = (zzlp) this.zza;
        zzlp r22 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
    L43:
        this.zzg = r22;
        this.zza = null;
        this.zzc = 10;
        r9 = ((zzmc) r9).zza(r12.zza(), this);
        if (r9 == r0) goto L54;
        r1 = r22;
        goto L46
    L8:
        r12 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
    L40:
        zzng r93 = this.zzd.zzb();
        this.zzg = r12;
        this.zza = r12;
        this.zzc = 9;
        r9 = zzoa.zzi(r93, this);
        if (r9 == r0) goto L54;
        r22 = r12;
        goto L43
    L9:
        zzlp r13 = (zzlp) this.zza;
        zzlp r23 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
    L37:
        this.zzg = r23;
        this.zza = null;
        this.zzc = 8;
        if (((zzmc) r9).zza(r13.zza(), this) == r0) goto L54;
    L33:
        r12 = r23;
        goto L40
    L10:
        zzmg r14 = (zzmg) this.zza;
        r23 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
    L31:
        this.zzg = r23;
        this.zza = null;
        this.zzc = 6;
        if (((zzmb) r9).zza(r14, this) == r0) goto L54;
    L11:
        zzaks r15 = (zzaks) this.zza;
        r23 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
    L28:
        zzmg r94 = this.zzf;
        zznm r4 = this.zzd.zzc();
        this.zzg = r23;
        this.zza = r94;
        this.zzc = 5;
        Object r16 = zzoa.zze(r4, r15, this);
        if (r16 == r0) goto L54;
        r14 = r94;
        r9 = r16;
        goto L31
    L12:
        zzmg r17 = (zzmg) this.zzb;
        zzaks r24 = (zzaks) this.zza;
        zzlp r42 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
    L25:
        this.zzg = r42;
        this.zza = r24;
        this.zzb = null;
        this.zzc = 4;
        if (((zzmb) r9).zza(r17, this) == r0) goto L54;
        r15 = r24;
        r23 = r42;
        goto L28
    L13:
        zzaki r18 = (zzaki) this.zza;
        zzlp r25 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
        zzaki r26 = r18;
        r13 = r25;
    L20:
        zzaks r95 = (zzaks) r9;
        if (this.zze.zzi() == false) goto L34;
        zzmg r43 = this.zzf;
        zzni r5 = this.zzd.zza();
        this.zzg = r13;
        this.zza = r95;
        this.zzb = r43;
        this.zzc = 3;
        Object r27 = zzoa.zze(r5, r26, this);
        if (r27 == r0) goto L54;
        r24 = r95;
        r9 = r27;
        r42 = r13;
        r17 = r43;
        goto L25
    L34:
        zzon r96 = this.zzd;
        this.zzg = r13;
        this.zza = r13;
        this.zzc = 7;
        r9 = r96.zzh(this);
        if (r9 == r0) goto L54;
        r23 = r13;
        goto L37
    L14:
        r13 = (zzlp) this.zzg;
        ResultKt.throwOnFailure(r9);
    L17:
        zzakg r44 = this.zze;
        zzaki r97 = (zzaki) r9;
        this.zzg = r13;
        this.zza = r97;
        this.zzc = 2;
        zzakr r52 = zzaks.zzd();
        r52.zza(r44.zzh());
        r52.zzb("18.9.2");
        r52.zzc(zzhs.zza(r44.zzb()));
        zzafv r28 = r52.zzp();
        if (r28 == r0) goto L54;
        r26 = r97;
        r9 = r28;
        goto L20
    L15:
        ResultKt.throwOnFailure(r9);
        r13 = (zzlp) this.zzg;
        zzakg r98 = this.zze;
        this.zzg = r13;
        this.zzc = 1;
        zzakh r45 = zzaki.zzd();
        zzakf r53 = (zzakf) r98.zzG();
        zzakd r6 = (zzakd) r98.zzg().zzG();
        r6.zzb();
        r53.zzb((zzake) r6.zzp());
        zzakj r62 = (zzakj) r98.zzd().zzG();
        r62.zzb();
        r62.zzd();
        r62.zzf();
        r62.zzh();
        r53.zza((zzakk) r62.zzp());
        r53.zzd();
        r45.zza((zzakg) r53.zzp());
        r45.zzb("18.9.2");
        r45.zzc(zzhs.zza(r98.zzb()));
        r9 = r45.zzp();
        if (r9 == r0) goto L54;
        goto L17
    }
}
