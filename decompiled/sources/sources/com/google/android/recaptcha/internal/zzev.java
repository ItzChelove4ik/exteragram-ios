package com.google.android.recaptcha.internal;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.TimeoutCancellationException;

/* loaded from: classes5.dex */
public interface zzev {
    int zza();

    boolean zzb();

    Object zzc(String r1, zzaka r2, Continuation r3);

    default Object zzd(Exception r3, Continuation r4) {
        int r0 = 27;
        if ((r3 instanceof TimeoutCancellationException) == false) goto L6;
    L11:
        int r32 = zza();
        zzalw r42 = zzalx.zza();
        r42.zza(r32);
        r42.zzb(13);
        r42.zzc(r0);
        return zzew.zzb(this, (zzalx) r42.zzp());
    L6:
        if ((r3 instanceof zzhg) == true) goto L8;
    L10:
        r0 = 2;
        goto L11
    L8:
        if (Intrinsics.areEqual(((zzhg) r3).zzb(), zzhe.zzb) == false) goto L10;
        goto L10
    }

    default Object zzg(zzakk r3, Continuation r4) {
        zzet r0 = new zzet(this, r3, null);
        return zzme.zza(36, zza(), r0, r4);
    }

    default Object zzh(String r2, zzaka r3, Continuation r4) {
        return new zzmc(new zzes(this, r2, r3, null));
    }

    default Object zzk(zzakk r1, Continuation r2) {
        return new zzmc(new zzeu(null));
    }
}
