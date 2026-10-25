package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final class zzkh implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzlj zzb;

    public zzkh(zzlj r1, long r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlj r0 = this.zzb;
        r0.zzg();
        r0.zzb();
        zzic r1 = r0.zzu;
        r1.zzaW().zzj().zza("Resetting analytics data (FE)");
        zzic r2 = r0.zzu;
        zzoc r3 = r2.zzh();
        r3.zzg();
        r3.zzb.zzc();
        r2.zzv().zzi();
        boolean r32 = !r0.zzu.zzB();
        zzhh r12 = r1.zzd();
        r12.zzc.zzb(this.zza);
        zzic r8 = r12.zzu;
        if (TextUtils.isEmpty(r8.zzd().zzq.zza()) == true) goto L5;
        r12.zzq.zzb(null);
    L5:
        r12.zzk.zzb(0);
        r12.zzl.zzb(0);
        if (r8.zzc().zzt() == true) goto L8;
        r12.zzm(r32);
    L8:
        r12.zzr.zzb(null);
        r12.zzs.zzb(0);
        r12.zzt.zzb(null);
        r2.zzt().zzB();
        r2.zzh().zza.zza();
        r0.zzc = r32;
        r2.zzt().zzC(new AtomicReference());
    }
}
