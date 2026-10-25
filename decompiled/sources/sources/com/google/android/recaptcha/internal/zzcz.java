package com.google.android.recaptcha.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Deferred;

/* loaded from: classes5.dex */
public final class zzcz {
    final /* synthetic */ zzdo zza;
    private final String zzb;
    private final Deferred zzc;
    private final zzaka zzd;
    private zzaka zze;
    private boolean zzf;

    public zzcz(zzdo r1, String r2, Deferred r3, zzaka r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r4;
        this.zzf = true;
    }

    public static final /* synthetic */ boolean zzb(zzcz r0) {
        return r0.zzf;
    }

    public static final /* synthetic */ Deferred zzc(zzcz r0) {
        return r0.zzc;
    }

    public static final /* synthetic */ void zzd(zzcz r0, boolean r1) {
        r0.zzf = false;
    }

    public static final /* synthetic */ void zze(zzcz r0, zzaka r1) {
        r0.zze = r1;
    }

    public static final /* synthetic */ String zzf(zzcz r0) {
        return r0.zzb;
    }

    public static final /* synthetic */ zzaka zzg(zzcz r0) {
        return r0.zze;
    }

    public final boolean zza(String r1) {
        zzaka r0 = this.zze;
        if (r0 == null) goto L5;
        String r02 = r0.zza();
    L7:
        return Intrinsics.areEqual(r02, r1);
    L5:
        r02 = null;
        goto L7
    }
}
