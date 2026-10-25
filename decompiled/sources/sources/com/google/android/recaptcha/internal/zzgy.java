package com.google.android.recaptcha.internal;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.Deferred;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzgy {
    private final zzne zza;
    private Deferred zzb;
    private final zzmq zzc;
    private final zzhu zzd;

    public zzgy(zzmq r1, zzne r2, zzhu r3) {
        this.zzc = r1;
        this.zza = r2;
        this.zzd = r3;
    }

    public static final /* synthetic */ Deferred zzd(zzgy r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ void zze(zzgy r0, Deferred r1) {
        r0.zzb = r1;
    }

    public final zzne zza() {
        return this.zza;
    }

    public final Object zzb(Continuation r2) {
        return new zzmc(new zzgt(this, null));
    }

    public final Object zzc(Continuation r7) {
        if ((r7 instanceof zzgq) == false) goto L7;
        zzgq r0 = (zzgq) r7;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        ResultKt.throwOnFailure(r72);
    L27:
        String r73 = (String) r72;
    L29:
        if (r73 != null) goto L31;
        return null;
    L31:
        if (r73.length() != 0) goto L33;
        return null;
    L33:
        return r73;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r72);
    L18:
        String r74 = (String) r72;
        if (r74 == null) goto L21;
        return r74;
    L21:
        monitor-enter(this);
        Deferred r75 = this.zzb;     // Catch: Throwable -> L35
        monitor-exit(this);
        if (r75 == null) goto L28;
        r0.zzc = 2;
        r72 = r75.await(r0);
        if (r72 != r12) goto L27;
    L38:
        return r12;
    L28:
        r73 = null;
    L35:
        th = move-exception;
        throw th;
    L16:
        ResultKt.throwOnFailure(r72);
        zzne r76 = this.zza;
        r0.zzc = 1;
        r72 = r76.zzc("ovk", r0);
        if (r72 == r12) goto L38;
    L7:
        r0 = new zzgq(this, r7);
        goto L8
    }

    public final zzmq zzf() {
        return this.zzc;
    }

    public final zzhu zzg() {
        return this.zzd;
    }
}
