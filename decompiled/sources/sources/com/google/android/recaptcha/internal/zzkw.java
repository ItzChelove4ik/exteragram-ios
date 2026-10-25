package com.google.android.recaptcha.internal;

import android.app.Application;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.TimeoutCancellationException;

/* loaded from: classes5.dex */
public final class zzkw implements zzkp {
    private final zzhq zza;
    private final zzmw zzb;
    private final zzhh zzc;
    private final zzhu zzd;

    public zzkw(Application r1, zzhq r2, zzmw r3, zzhh r4, zzhu r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
    }

    public static final /* synthetic */ zzhg zzb(zzkw r7, Exception r8) {
        if ((r8 instanceof TimeoutCancellationException) == false) goto L7;
        return r7.zzc.zza(r8, new zzhg(zzhf.zzc, zzhe.zzb, r8.getMessage(), null, 8, null));
    L7:
        if ((r8 instanceof zzhg) == false) goto L11;
        return r7.zzc.zza(r8, (zzhg) r8);
    L11:
        return r7.zzc.zza(r8, new zzhg(zzhf.zzc, zzhe.zzs, r8.getMessage(), null, 8, null));
    }

    public static final /* synthetic */ zzmw zzc(zzkw r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ zzhq zzd(zzkw r0) {
        return r0.zza;
    }

    public static final /* synthetic */ zzhu zze(zzkw r0) {
        return r0.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzkp
    public final Object zza(String r10, zzakg r11, zzaku r12, String r13, long r14, Continuation r16) {
        return new zzmc(new zzks(r10, r12, r11, null, this, r14, null));
    }
}
