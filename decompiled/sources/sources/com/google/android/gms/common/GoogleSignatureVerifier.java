package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.internal.common.zzad;
import com.google.android.gms.internal.common.zzah;
import com.google.android.gms.internal.common.zzal;
import java.util.Arrays;

/* loaded from: classes.dex */
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zza;
    private final Context zzb;
    private volatile String zzc;

    public GoogleSignatureVerifier(Context r1) {
        this.zzb = r1.getApplicationContext();
    }

    public static GoogleSignatureVerifier getInstance(Context r2) {
        Preconditions.checkNotNull(r2);
        monitor-enter(GoogleSignatureVerifier.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zza != null) goto L9;
        zzo.zza(r2);     // Catch: Throwable -> L7
        zza = new GoogleSignatureVerifier(r2);     // Catch: Throwable -> L7
    L9:
        monitor-exit(GoogleSignatureVerifier.class);     // Catch: Throwable -> L7
        return zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public static final boolean zza(PackageInfo r10, boolean r11) {
        if (r10 != null) goto L6;
        return false;
    L6:
        if (r11 == true) goto L8;
    L66:
        if (r11 == false) goto L19;
        zzah r2 = zzn.zzc;     // Catch: IllegalArgumentException -> L59
    L20:
        int r3 = AndroidUtilsLight.$r8$clinit;     // Catch: IllegalArgumentException -> L59
        int r32 = Build.VERSION.SDK_INT;     // Catch: IllegalArgumentException -> L59
        if (r32 >= 28) goto L30;
        Signature[] r33 = r10.signatures;     // Catch: IllegalArgumentException -> L59
        byte[] r4 = null;
        if (r33 != null) goto L25;
    L27:
        if (r4 == null) goto L29;
        zzah r34 = zzah.zzk(r4);     // Catch: IllegalArgumentException -> L59
    L46:
        if (r34.isEmpty() == true) goto L58;
        zzah r35 = r34.zzh();     // Catch: IllegalArgumentException -> L59
        int r42 = r35.size();     // Catch: IllegalArgumentException -> L59
        int r5 = 0;
    L48:
        if (r5 >= r42) goto L56;
        byte[] r6 = (byte[]) r35.get(r5);     // Catch: IllegalArgumentException -> L59
        zzal r7 = r2.zzr(0);     // Catch: IllegalArgumentException -> L59
    L50:
        int r9 = r5 + 1;     // Catch: IllegalArgumentException -> L59
        if (r7.hasNext() == false) goto L55;
        if (Arrays.equals(r6, (byte[]) r7.next()) == false) goto L50;
        return true;
    L55:
        r5 = r9;
        goto L48
    L56:
        return false;
    L58:
        throw new IllegalArgumentException("Unable to obtain package certificate history.");     // Catch: IllegalArgumentException -> L59
    L29:
        r34 = zzah.zzj();     // Catch: IllegalArgumentException -> L59
        goto L46
    L25:
        if (r33.length != 1) goto L27;
        r4 = r33[0].toByteArray();     // Catch: IllegalArgumentException -> L59
        goto L27
    L30:
        if (r32 < 28) goto L32;
        boolean r36 = true;
    L33:
        com.google.android.gms.internal.common.zzr.zza(r36);     // Catch: IllegalArgumentException -> L59
        SigningInfo r37 = GoogleSignatureVerifier$$ExternalSyntheticApiModelOutline0.m(r10);     // Catch: IllegalArgumentException -> L59
        if (r37 != null) goto L36;
    L44:
        r34 = zzah.zzj();     // Catch: IllegalArgumentException -> L59
        goto L46
    L36:
        if (r37.hasMultipleSigners() == true) goto L44;
        if (r37.getSigningCertificateHistory() == null) goto L44;
        int r43 = zzah.$r8$clinit;     // Catch: IllegalArgumentException -> L59
        zzad r44 = new zzad();     // Catch: IllegalArgumentException -> L59
        Signature[] r38 = r37.getSigningCertificateHistory();     // Catch: IllegalArgumentException -> L59
        int r52 = r38.length;     // Catch: IllegalArgumentException -> L59
        int r62 = 0;
    L41:
        if (r62 >= r52) goto L43;
        r44.zzb(r38[r62].toByteArray());     // Catch: IllegalArgumentException -> L59
        r62 = r62 + 1;     // Catch: IllegalArgumentException -> L59
        goto L41
    L43:
        r34 = r44.zzd();     // Catch: IllegalArgumentException -> L59
        goto L46
    L32:
        r36 = false;
        goto L33
    L19:
        r2 = zzn.zzb;     // Catch: IllegalArgumentException -> L59
    L59:
        Log.i("GoogleSignatureVerifier", "package info is not set correctly");
        if (r11 == false) goto L62;
        zzj r102 = zzc(r10, zzn.zza);
    L63:
        if (r102 == null) goto L65;
        return true;
    L65:
        return false;
    L62:
        r102 = zzc(r10, new zzj[]{zzn.zza[0]});
        goto L63
    L8:
        if ("com.android.vending".equals(r10.packageName) == false) goto L10;
    L11:
        ApplicationInfo r112 = r10.applicationInfo;
        if (r112 != null) goto L15;
    L13:
        r11 = false;
        goto L66
    L15:
        if ((r112.flags & 129) == 0) goto L13;
        r11 = true;
        goto L66
    L10:
        if ("com.google.android.gms".equals(r10.packageName) == false) goto L66;
        goto L11
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v4, types: [android.os.StrictMode$ThreadPolicy] */
    /* JADX WARN: Type inference failed for: r8v6, types: [int] */
    @SuppressLint({"PackageManagerGetSignatures"})
    private final zzy zzb(String r6, boolean r7, boolean r8) {
        if (r6 != null) goto L7;
        return zzy.zzc("null pkg");
    L7:
        if (r6.equals(this.zzc) == true) goto L54;
        zzm r82 = zzo.zza;
        StrictMode.ThreadPolicy r83 = StrictMode.allowThreadDiskReads();
        zzo.zzb();     // Catch: Throwable -> L13 RemoteException -> L15 Throwable -> L17
        boolean r1 = zzo.zzg.zzg();     // Catch: Throwable -> L13 RemoteException -> L15 Throwable -> L17
        StrictMode.setThreadPolicy(r83);
        if (r1 == false) goto L20;
        zzv r72 = new zzv(null);
        r72.zza(r6);
        r72.zzb(GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb));
        r72.zzc(true);
        zzy r73 = zzo.zzc(r72.zzd());
    L45:
        if (r73.zza == false) goto L47;
        this.zzc = r6;
    L47:
        return r73;
    L20:
        r83 = Build.VERSION.SDK_INT;
        if (r83 < 28) goto L23;
        int r84 = 134217792;
    L57:
        PackageInfo r85 = this.zzb.getPackageManager().getPackageInfo(r6, r84);     // Catch: PackageManager.NameNotFoundException -> L48
        boolean r12 = GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb);
        if (r85 != null) goto L28;
        r73 = zzy.zzc("null pkg");
        goto L45
    L28:
        Signature[] r74 = r85.signatures;
        if (r74 != null) goto L31;
    L43:
        r73 = zzy.zzc("single cert required");
        goto L45
    L31:
        if (r74.length != 1) goto L43;
        zzk r75 = new zzk(r85.signatures[0].toByteArray());
        String r2 = r85.packageName;
        zzy r13 = zzo.zzd(r2, r75, r12, false);
        if (r13.zza == false) goto L42;
        ApplicationInfo r86 = r85.applicationInfo;
        if (r86 == null) goto L42;
        if ((r86.flags & 2) == 0) goto L42;
        if (zzo.zzd(r2, r75, false, true).zza == false) goto L42;
        r73 = zzy.zzc("debuggable release cert app rejected");
    L42:
        r73 = r13;
    L48:
        e = move-exception;
        return zzy.zzd("no pkg ".concat(r6), e);
    L23:
        r84 = 64;
    L13:
        th = move-exception;
        StrictMode.setThreadPolicy(r83);
        throw th;
    L17:
        e = move-exception;
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);     // Catch: Throwable -> L13
        StrictMode.setThreadPolicy(r83);
        goto L20
    L54:
        return zzy.zzb();
    }

    private static zzj zzc(PackageInfo r3, zzj... r4) {
        Signature[] r0 = r3.signatures;
        if (r0 != null) goto L6;
    L17:
        return null;
    L6:
        if (r0.length == 1) goto L9;
        Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
        return null;
    L9:
        int r2 = 0;
        zzk r02 = new zzk(r3.signatures[0].toByteArray());
    L11:
        if (r2 >= r4.length) goto L17;
        if (r4[r2].equals(r02) == true) goto L15;
        r2 = r2 + 1;
        goto L11
    L15:
        return r4[r2];
    }

    public boolean isGooglePublicSignedPackage(PackageInfo r4) {
        if (r4 != null) goto L6;
        return false;
    L6:
        if (zza(r4, false) == false) goto L9;
        return true;
    L9:
        if (zza(r4, true) == true) goto L11;
    L14:
        return false;
    L11:
        if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.zzb) == false) goto L13;
        return true;
    L13:
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        goto L14
    }

    public boolean isUidGoogleSigned(int r6) {
        String[] r62 = this.zzb.getPackageManager().getPackagesForUid(r6);
        if (r62 == null) goto L14;
        int r0 = r62.length;
        if (r0 == 0) goto L14;
        zzy r2 = null;
        int r3 = 0;
    L8:
        if (r3 >= r0) goto L13;
        r2 = zzb(r62[r3], false, false);
        if (r2.zza == true) goto L15;
        r3 = r3 + 1;
    L15:
        r2.zze();
        return r2.zza;
    L13:
        Preconditions.checkNotNull(r2);
    L14:
        r2 = zzy.zzc("no pkgs");
        goto L15
    }
}
