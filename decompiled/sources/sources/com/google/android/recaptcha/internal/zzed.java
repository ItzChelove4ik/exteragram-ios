package com.google.android.recaptcha.internal;

import android.os.Build;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
final class zzed extends SuspendLambda implements Function2 {
    final /* synthetic */ zzee zza;

    public zzed(zzee r1, Continuation r2) {
        this.zza = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r1, Continuation r2) {
        return new zzed(this.zza, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzed) create((zzmg) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r21) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r21);
        zzaly r0 = zzamb.zzb();
        zzee r1 = this.zza;
        zzama r3 = zzee.zze(r1, Build.MANUFACTURER);
        zzama r4 = zzee.zze(r1, Build.MODEL);
        zzama r5 = zzee.zze(r1, Build.DEVICE);
        zzama r6 = zzee.zze(r1, Build.HARDWARE);
        zzama r7 = zzee.zze(r1, Build.FINGERPRINT);
        zzama r8 = zzee.zze(r1, Build.PRODUCT);
        zzama r9 = zzee.zze(r1, Build.BOARD);
        zzama r10 = zzee.zze(r1, Build.BRAND);
        zzama r11 = zzee.zze(r1, ArraysKt.joinToString$default(Build.SUPPORTED_ABIS, ",", "[", "]", 0, null, null, 56, null));
        long r12 = Build.TIME;
        zzalz r2 = zzama.zza();
        r2.zzb(r12);
        r0.zzc(CollectionsKt.listOf(new zzama[]{r3, r4, r5, r6, r7, r8, r9, r10, r11, (zzama) r2.zzp(), zzee.zze(r1, Build.ID), zzee.zze(r1, Build.BOOTLOADER), zzee.zze(r1, Build.DISPLAY), zzee.zze(r1, Build.TYPE), zzee.zze(r1, Build.TAGS)}));
        return zzew.zza(r1, (zzamb) r0.zzp());
    }
}
