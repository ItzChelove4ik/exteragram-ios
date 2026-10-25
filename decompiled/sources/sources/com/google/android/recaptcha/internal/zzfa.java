package com.google.android.recaptcha.internal;

import java.nio.charset.StandardCharsets;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzfa extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzaka zzc;
    final /* synthetic */ zzmg zzd;
    final /* synthetic */ zzfd zze;

    public zzfa(zzaka r1, zzmg r2, zzfd r3, Continuation r4) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzfa(this.zzc, this.zzd, this.zze, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzfa) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zzb;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L7;
        ResultKt.throwOnFailure(r11);     // Catch: Exception -> L27
    L24:
        zzfd r10 = this.zze;     // Catch: Exception -> L27
        zzaly r02 = zzamb.zzb();     // Catch: Exception -> L27
        zzalz r12 = zzama.zza();     // Catch: Exception -> L27
        r12.zzc((String) r11);     // Catch: Exception -> L27
        r02.zza((zzama) r12.zzp());     // Catch: Exception -> L27
        return zzew.zza(r10, (zzamb) r02.zzp());
    L7:
        zzmg r13 = (zzmg) this.zza;
        ResultKt.throwOnFailure(r11);     // Catch: Exception -> L27
    L21:
        this.zza = null;     // Catch: Exception -> L27
        this.zzb = 2;     // Catch: Exception -> L27
        r11 = ((zzmc) r11).zza(r13, this);     // Catch: Exception -> L27
        if (r11 != r0) goto L24;
    L26:
        return r0;
    L10:
        ResultKt.throwOnFailure(r11);
        zzaka r112 = this.zzc;
        if (r112 == null) goto L15;
        zzaer r113 = r112.zzb();
        if (r113 == null) goto L15;
        zzrv r14 = zzrv.zzi();
        byte[] r114 = r113.zzy();
        byte[] r115 = zzri.zza().zza(r14.zzg(r114, 0, r114.length), StandardCharsets.UTF_8).zzb();
        zzaer r116 = zzaer.zzs(r115, 0, r115.length);
        zzrv r15 = zzrv.zzj();
        byte[] r117 = r116.zzy();
        String r118 = r15.zzg(r117, 0, r117.length);
    L16:
        if (r118 == null) goto L31;
        if (r118.length() == 0) goto L31;
        r13 = this.zzd;     // Catch: Exception -> L27
        zzfp r4 = zzfd.zzi(this.zze);     // Catch: Exception -> L27
        this.zza = r13;     // Catch: Exception -> L27
        this.zzb = 1;     // Catch: Exception -> L27
        r11 = new zzmc(new zzfe(r4, r118, null));     // Catch: Exception -> L27
        if (r11 == r0) goto L26;
    L31:
        throw new zzhg(zzhf.zzb, zzhe.zzav, null, null, 12, null);
    L15:
        r118 = null;
    L27:
        e = move-exception;
        throw new zzhg(zzhf.zzb, zzhe.zzt, e.getMessage(), e);
    }
}
