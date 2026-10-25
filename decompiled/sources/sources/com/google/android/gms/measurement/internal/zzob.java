package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzob {
    final /* synthetic */ zzoc zza;

    public zzob(zzoc r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    public final void zza() {
        zzoc r0 = this.zza;
        r0.zzg();
        zzic r02 = r0.zzu;
        if (r02.zzd().zzo(r02.zzba().currentTimeMillis()) == false) goto L13;
        r02.zzd().zzg.zzb(true);
        ActivityManager.RunningAppProcessInfo r1 = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(r1);
        if (r1.importance != 100) goto L14;
        r02.zzaW().zzk().zza("Detected application was in foreground");
        long r3 = r02.zzba().currentTimeMillis();
        if (r02.zzc().zzp(null, zzfy.zzbe) == false) goto L10;
        long r03 = r02.zzba().elapsedRealtime();
    L11:
        zzc(r3, r03, false);
        return;
    L10:
        r03 = 0;
        goto L11
    L14:
        return;
    }

    public final void zzb(long r5, long r7, boolean r9) {
        zzoc r0 = this.zza;
        r0.zzg();
        r0.zzj();
        zzic r1 = r0.zzu;
        if (r1.zzd().zzo(r5) == false) goto L5;
        r1.zzd().zzg.zzb(true);
        r0.zzu.zzv().zzi();
    L5:
        r1.zzd().zzk.zzb(r5);
        if (r1.zzd().zzg.zza() == false) goto L9;
        zzc(r5, r7, r9);
        return;
    }

    public final void zzc(long r11, long r13, boolean r15) {
        zzoc r0 = this.zza;
        r0.zzg();
        if (r0.zzu.zzB() == false) goto L10;
        zzic r8 = r0.zzu;
        r8.zzd().zzk.zzb(r11);
        long r1 = r8.zzba().elapsedRealtime();
        r8.zzaW().zzk().zzb("Session started, time", Long.valueOf(r1));
        long r6 = r11 / 1000;
        zzic r9 = r0.zzu;
        Long r3 = Long.valueOf(r6);
        r9.zzj().zzM("auto", "_sid", r3, r11);
        r8.zzd().zzl.zzb(r6);
        r8.zzd().zzg.zzb(false);
        Bundle r02 = new Bundle();
        r02.putLong("_sid", r6);
        r9.zzj().zzF("auto", "_s", r11, r13, r02);
        String r03 = r8.zzd().zzq.zza();
        if (TextUtils.isEmpty(r03) == true) goto L9;
        Bundle r7 = new Bundle();
        r7.putString("_ffr", r03);
        r9.zzj().zzF("auto", "_ssr", r11, r13, r7);
        return;
    L9:
        return;
    }
}
