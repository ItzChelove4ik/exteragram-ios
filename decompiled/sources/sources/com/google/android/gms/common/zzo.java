package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzad;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import java.security.MessageDigest;

/* loaded from: classes.dex */
abstract class zzo {
    static final zzm zza = null;
    static final zzm zzb = null;
    static final zzm zzc = null;
    static final zzm zzd = null;
    static final zzm zze = null;
    static final zzm zzf = null;
    static volatile zzad zzg;
    private static final Object zzi = null;
    private static Context zzj;

    static {
        zza = new zzd(zzj.zzf("0\u0082\u0005È0\u0082\u0003° \u0003\u0002\u0001\u0002\u0002\u0014\u007f¢fú§p\u0085xb±"));
        zzb = new zze(zzj.zzf("0\u0082\u0006\u00040\u0082\u0003ì \u0003\u0002\u0001\u0002\u0002\u0014QÕÛ\u0004÷XçB\u0086<"));
        zzc = new zzf(zzj.zzf("0\u0082\u0005È0\u0082\u0003° \u0003\u0002\u0001\u0002\u0002\u0014\u0010\u008ae\bsù/\u008eQí"));
        zzd = new zzg(zzj.zzf("0\u0082\u0006\u00040\u0082\u0003ì \u0003\u0002\u0001\u0002\u0002\u0014\u0003£²\u00ad×árÊkì"));
        zze = new zzh(zzj.zzf("0\u0082\u0004C0\u0082\u0003+ \u0003\u0002\u0001\u0002\u0002\t\u0000Âà\u0087FdJ0\u008d0"));
        zzf = new zzi(zzj.zzf("0\u0082\u0004¨0\u0082\u0003\u0090 \u0003\u0002\u0001\u0002\u0002\t\u0000Õ\u0085¸l}ÓNõ0"));
        zzi = new Object();
    }

    public static synchronized void zza(Context r2) {
        monitor-enter(zzo.class);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (zzj != null) goto L14;
        if (r2 == null) goto L12;
        zzj = r2.getApplicationContext();     // Catch: Throwable -> L10
        monitor-exit(zzo.class);
        return;
    L12:
        monitor-exit(zzo.class);
        return;
    L14:
        Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");     // Catch: Throwable -> L10
        monitor-exit(zzo.class);
    }

    public static void zzb() {
        if (zzg == null) goto L5;
        return;
    L5:
        Preconditions.checkNotNull(zzj);
        Object r0 = zzi;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L8:
        if (zzg != null) goto L12;
        zzg = zzac.zzb(DynamiteModule.load(zzj, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));     // Catch: Throwable -> L10
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L10
    }

    public static zzy zzc(zzw r5) {
        StrictMode.ThreadPolicy r2 = StrictMode.allowThreadDiskReads();
        Preconditions.checkNotNull(zzj);     // Catch: Throwable -> L9
        zzb();     // Catch: Throwable -> L9 DynamiteModule.LoadingException -> L26
        Preconditions.checkNotNull(zzj);     // Catch: Throwable -> L9
        zzp r3 = r5.zzb(zzj);     // Catch: Throwable -> L9
    L11:
        e = move-exception;
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);     // Catch: Throwable -> L9
        zzy r52 = zzy.zzd("module call", e);     // Catch: Throwable -> L9
    L28:
        StrictMode.setThreadPolicy(r2);
        return r52;
    L7:
        if (r5.zza() == false) goto L13;
        zzr r53 = zzg.zzf(r3);     // Catch: Throwable -> L9 RemoteException -> L11
    L15:
        if (r53.zza() == false) goto L17;
        r52 = zzy.zzf(r53.zze(), r53.zzc());     // Catch: Throwable -> L9
        goto L28
    L17:
        String r0 = r53.zzb();     // Catch: Throwable -> L9
        if (r53.zzd() != 4) goto L20;
        PackageManager.NameNotFoundException r1 = new PackageManager.NameNotFoundException();     // Catch: Throwable -> L9
    L22:
        if (r0 != null) goto L24;
        r0 = "error checking package certificate";
    L24:
        r52 = zzy.zzg(r53.zze(), r53.zzd(), r0, r1);     // Catch: Throwable -> L9
        goto L28
    L20:
        r1 = null;
        goto L22
    L13:
        r53 = zzg.zzh(r3);     // Catch: Throwable -> L9 RemoteException -> L11
        goto L15
    L26:
        e = move-exception;
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);     // Catch: Throwable -> L9
        r52 = zzy.zzd("module init: ".concat(String.valueOf(e.getMessage())), e);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        StrictMode.setThreadPolicy(r2);
        throw th;
    }

    @Deprecated
    public static zzy zzd(String r1, zzj r2, boolean r3, boolean r4) {
        StrictMode.ThreadPolicy r0 = StrictMode.allowThreadDiskReads();
        zzy r12 = zzf(r1, r2, r3, r4);     // Catch: Throwable -> L6
        StrictMode.setThreadPolicy(r0);
        return r12;
    L6:
        th = move-exception;
        StrictMode.setThreadPolicy(r0);
        throw th;
    }

    public static /* synthetic */ String zze(boolean r2, String r3, zzj r4) {
        if (r2 == false) goto L4;
    L6:
        String r0 = "not allowed";
    L7:
        MessageDigest r1 = AndroidUtilsLight.zza("SHA-256");
        Preconditions.checkNotNull(r1);
        return String.format("%s: pkg=%s, sha256=%s, atk=%s, ver=%s", new Object[]{r0, r3, Hex.bytesToStringLowercase(r1.digest(r4.zzc())), Boolean.valueOf(r2), "12451000.false"});
    L4:
        if (zzf(r3, r4, true, false).zza == false) goto L6;
        r0 = "debug cert rejected";
        goto L7
    }

    @Deprecated
    private static zzy zzf(String r4, zzj r5, boolean r6, boolean r7) {
        zzb();     // Catch: DynamiteModule.LoadingException -> L14
        Preconditions.checkNotNull(zzj);
        zzt r2 = new zzt(r4, r5, r6, r7);
        if (zzg.zze(r2, ObjectWrapper.wrap(zzj.getPackageManager())) == false) goto L10;
        return zzy.zzb();
    L10:
        return new zzx(new zzl(r6, r4, r5), null);
    L11:
        e = move-exception;
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
        return zzy.zzd("module call", e);
    L14:
        e = move-exception;
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
        return zzy.zzd("module init: ".concat(String.valueOf(e.getMessage())), e);
    }
}
