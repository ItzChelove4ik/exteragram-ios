package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzjy implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ zzlj zzb;

    public zzjy(zzlj r1, boolean r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlj r0 = this.zzb;
        zzic r1 = r0.zzu;
        boolean r2 = r1.zzB();
        boolean r3 = r1.zzA();
        boolean r6 = this.zza;
        r1.zzz(r6);
        if (r3 != r6) goto L6;
        r1.zzaW().zzk().zzb("Default data collection state already set to", Boolean.valueOf(r6));
    L6:
        if (r1.zzB() != r2) goto L8;
    L9:
        r1.zzaW().zzh().zzc("Default data collection is different than actual status", Boolean.valueOf(r6), Boolean.valueOf(r2));
    L10:
        r0.zzak();
        return;
    L8:
        if (r1.zzB() == r1.zzA()) goto L10;
        goto L9
    }
}
