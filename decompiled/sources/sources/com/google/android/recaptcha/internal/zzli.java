package com.google.android.recaptcha.internal;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.Locale;
import java.util.Set;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzli {
    private final Application zza;
    private final zzhh zzb;
    private zzaiw zzc;

    public zzli(Application r1, zzhh r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    private static final String zzb() {
        return Locale.getDefault().getISO3Country();
    L4:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private static final String zzc() {
        return Locale.getDefault().getISO3Language();
    L4:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public final zzajr zza(String r11) {
        Set r0 = this.zzb.zzb();
        zzaiw r1 = this.zzc;
        if (r1 != null) goto L24;
        zzaiu r12 = zzaiw.zza();
        int r2 = Build.VERSION.SDK_INT;
        r12.zza(r2);
        Application r3 = this.zza;
        String r6 = "unknown";
        if (r2 < 33) goto L10;
        int r22 = r3.getPackageManager().getApplicationInfo(r3.getPackageName(), PackageManager.ApplicationInfoFlags.of(128)).metaData.getInt("com.google.android.gms.version", -1);     // Catch: PackageManager.NameNotFoundException -> L27
        if (r22 == (-1)) goto L8;
        String r23 = String.valueOf(r22);     // Catch: PackageManager.NameNotFoundException -> L27
    L14:
        r12.zzb(r23);
        r12.zzc("18.9.2");
        r12.zzd(Build.MODEL);
        r12.zze(Build.MANUFACTURER);
        int r24 = Build.VERSION.SDK_INT;     // Catch: PackageManager.NameNotFoundException -> L26
        if (r24 < 33) goto L19;
        Application r25 = this.zza;     // Catch: PackageManager.NameNotFoundException -> L26
        r6 = String.valueOf(r25.getPackageManager().getPackageInfo(r25.getPackageName(), PackageManager.PackageInfoFlags.of(0)).getLongVersionCode());     // Catch: PackageManager.NameNotFoundException -> L26
    L30:
        goto L23
    L19:
        Application r32 = this.zza;
        if (r24 < 28) goto L22;
        r6 = String.valueOf(r32.getPackageManager().getPackageInfo(r32.getPackageName(), 0).getLongVersionCode());     // Catch: PackageManager.NameNotFoundException -> L26
        goto L30
    L22:
        r6 = String.valueOf(r32.getPackageManager().getPackageInfo(r32.getPackageName(), 0).versionCode);     // Catch: PackageManager.NameNotFoundException -> L26
    L23:
        r12.zzf(r6);
        r1 = (zzaiw) r12.zzp();
        goto L24
    L10:
        int r26 = r3.getPackageManager().getApplicationInfo(r3.getPackageName(), 128).metaData.getInt("com.google.android.gms.version", -1);     // Catch: PackageManager.NameNotFoundException -> L27
        if (r26 == (-1)) goto L8;
        r23 = String.valueOf(r26);     // Catch: PackageManager.NameNotFoundException -> L27
    L8:
        r23 = "unknown";
    L24:
        this.zzc = r1;
        zzaiu r10 = (zzaiu) r1.zzG();
        r10.zzg(r0);
        zzaiw r102 = (zzaiw) r10.zzp();
        zzajq r02 = zzajr.zza();
        r02.zza(r11);
        r02.zzb(r102);
        r02.zzc(zzc());
        r02.zzd(zzb());
        return (zzajr) r02.zzp();
    }
}
