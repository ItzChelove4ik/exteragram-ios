package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.RecaptchaAction;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzke implements zzkn {
    private CompletableDeferred zza;
    private zzhg zzb;
    private zzakg zzc;
    private zzkm zzd;
    private final zziz zze;
    private final zzhu zzf;

    public zzke(zzhu r1, zziz r2, zzhl r3) {
        this.zzf = r1;
        this.zze = r2;
        this.zza = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.zzd = zzkm.zza();
    }

    public static final /* synthetic */ Object zzd(zzke r0, long r1, Continuation r3) {
        return r0.zzp(r1, r3);
    }

    public static final /* synthetic */ boolean zze(zzke r3, Exception r4) {
        if ((r4 instanceof zzhg) == false) goto L13;
        zzhg r42 = (zzhg) r4;
        if (Intrinsics.areEqual(r42.zza(), zzhf.zzd) == false) goto L7;
    L12:
        return false;
    L7:
        if (Intrinsics.areEqual(r42.zza(), zzhf.zze) == true) goto L12;
        if (Intrinsics.areEqual(r42.zza(), zzhf.zzf) == false) goto L11;
        return false;
    L11:
        return true;
    L13:
        return true;
    }

    public static final /* synthetic */ zzhg zzf(zzke r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ void zzg(zzke r0, zzkm r1) {
        r0.zzd = r1;
    }

    public static final /* synthetic */ void zzh(zzke r0, CompletableDeferred r1) {
        r0.zza = r1;
    }

    public static final /* synthetic */ void zzi(zzke r0, zzakg r1) {
        r0.zzc = r1;
    }

    public static final /* synthetic */ void zzj(zzke r0, zzhg r1) {
        r0.zzb = r1;
    }

    public static final /* synthetic */ Object zzk(zzke r0, Function1 r1, Continuation r2) {
        return r0.zzq(r1, r2);
    }

    public static final /* synthetic */ CompletableDeferred zzl(zzke r0) {
        return r0.zza;
    }

    public static final /* synthetic */ zzakg zzm(zzke r0) {
        return r0.zzc;
    }

    public static final /* synthetic */ zziz zzn(zzke r0) {
        return r0.zze;
    }

    public static final /* synthetic */ zzhu zzo(zzke r0) {
        return r0.zzf;
    }

    private final Object zzp(long r2, Continuation r4) {
        return new zzmc(new zzkd(this, r2, null));
    }

    private final Object zzq(Function1 r5, Continuation r6) {
        if ((r6 instanceof zzjv) == false) goto L7;
        zzjv r0 = (zzjv) r6;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r4 = r0.zza;
        Object r62 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r0.zzc;
        if (r12 == 0) goto L14;
        if (r12 != 1) goto L12;
        zzhk r52 = r0.zzd;
        ResultKt.throwOnFailure(r4);
    L17:
        r52.zzb();
        return Boxing.boxLong(r52.zza(TimeUnit.MILLISECONDS));
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r4);
        zzhk r42 = new zzhk();
        r0.zzd = r42;
        r0.zzc = 1;
        if (r5.invoke(r0) == r62) goto L19;
        r52 = r42;
        goto L17
    L19:
        return r62;
    L7:
        r0 = new zzjv(this, r6);
        goto L8
    }

    public final zzkm zza() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzkn
    public final Object zzb(long r1, Continuation r3) {
        return zzp(r1, r3);
    }

    @Override // com.google.android.recaptcha.internal.zzkn
    public final Object zzc(String r9, RecaptchaAction r10, long r11, String r13, Continuation r14) {
        return new zzmc(new zzju(this, r11, r9, r10, null, null));
    }
}
