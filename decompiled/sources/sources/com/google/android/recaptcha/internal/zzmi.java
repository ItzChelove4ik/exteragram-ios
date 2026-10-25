package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzmi implements zzmt {
    private final zzmx zza;

    public zzmi(zzmx r1) {
        this.zza = r1;
    }

    @Override // com.google.android.recaptcha.internal.zzmt
    public final Object zza(String r8, zzamf r9, Continuation r10) {
        if ((r10 instanceof zzmh) == false) goto L7;
        zzmh r0 = (zzmh) r10;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L13;
        ResultKt.throwOnFailure(r102);     // Catch: Exception -> L35 zzhg -> L38
    L19:
        zznb r103 = (zznb) r102;     // Catch: Exception -> L35 zzhg -> L38
        return (zzakg) r103.zzd(zzakg.zzn());
    L22:
        e = move-exception;
        if (Intrinsics.areEqual(e.zzb(), zzhe.zzK) == true) goto L42;
        throw e;     // Catch: Exception -> L35 zzhg -> L38
    L42:
        zzami r7 = zzamh.zzb(r103.zza()).zza();     // Catch: Exception -> L31
        zzhg r72 = (zzhg) zzhg.zze().get(r7);     // Catch: Exception -> L31
        if (r72 != null) goto L47;
        zzhg r02 = new zzhg(zzhf.zza, zzhe.zzA, null, null, 12, null);     // Catch: Exception -> L31
    L48:
        throw r02;     // Catch: Exception -> L35 zzhg -> L38
    L47:
        throw r72;     // Catch: Exception -> L35 zzhg -> L38
    L31:
        e = move-exception;
        r02 = new zzhg(zzhf.zzc, zzhe.zzl, e.getMessage(), null, 8, null);     // Catch: Exception -> L35 zzhg -> L38
        goto L48
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r102);
        zzmx r73 = this.zza;     // Catch: Exception -> L35 zzhg -> L38
        byte[] r92 = r9.zzw();     // Catch: Exception -> L35 zzhg -> L38
        r0.zzc = 1;     // Catch: Exception -> L35 zzhg -> L38
        r102 = r73.zza(r8, r92, r0);     // Catch: Exception -> L35 zzhg -> L38
        if (r102 != r12) goto L19;
        return r12;
    L35:
        e = move-exception;
        throw new zzhg(zzhf.zzc, zzhe.zzk, e.getMessage(), null, 8, null);
    L38:
        e = move-exception;
        throw e;
    L7:
        r0 = new zzmh(this, r10);
        goto L8
    }
}
