package com.google.android.gms.measurement.internal;

import android.util.Log;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzgr implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Object zzc;
    final /* synthetic */ Object zzd;
    final /* synthetic */ Object zze;
    final /* synthetic */ zzgu zzf;

    public zzgr(zzgu r1, int r2, String r3, Object r4, Object r5, Object r6) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        Objects.requireNonNull(r1);
        this.zzf = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzgu r0 = this.zzf;
        zzhh r1 = r0.zzu.zzd();
        if (r1.zzv() == true) goto L5;
        Log.println(6, r0.zzn(), "Persisted config not initialized. Not logging error/warn");
        return;
    L5:
        if (r0.zzr() != 0) goto L11;
        zzic r2 = r0.zzu;
        if (r2.zzc().zzj() == false) goto L9;
        r2.zzaV();
        r0.zzs('C');
        goto L11
    L9:
        r2.zzaV();
        r0.zzs('c');
    L11:
        if (r0.zzt() >= 0) goto L13;
        r0.zzu.zzc().zzi();
        r0.zzu(161000);
    L13:
        int r22 = this.zza;
        char r3 = r0.zzr();
        long r4 = r0.zzt();
        String r02 = this.zzb;
        Object r6 = this.zzc;
        Object r7 = this.zzd;
        Object r10 = this.zze;
        char r23 = "01VDIWEA?".charAt(r22);
        String r102 = zzgu.zzo(true, r02, r6, r7, r10);
        int r62 = String.valueOf(r23).length();
        int r72 = String.valueOf(r3).length();
        int r63 = (r62 + 1) + r72;
        StringBuilder r8 = new StringBuilder(((r63 + String.valueOf(r4).length()) + 1) + r102.length());
        r8.append("2");
        r8.append(r23);
        r8.append(r3);
        r8.append(r4);
        r8.append(":");
        r8.append(r102);
        String r103 = r8.toString();
        if (r103.length() <= 1024) goto L16;
        r103 = r02.substring(0, 1024);
    L16:
        zzhf r03 = r1.zzb;
        if (r03 == null) goto L22;
        r03.zza(r103, 1);
        return;
    }
}
