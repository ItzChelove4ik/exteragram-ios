package com.google.android.gms.common.wrappers;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Binder;
import android.os.Process;
import com.google.android.gms.common.util.PlatformVersion;

/* loaded from: classes.dex */
public class PackageManagerWrapper {
    protected final Context zza;

    public PackageManagerWrapper(Context r1) {
        this.zza = r1;
    }

    public int checkCallingOrSelfPermission(String r1) {
        return this.zza.checkCallingOrSelfPermission(r1);
    }

    public int checkPermission(String r1, String r2) {
        return this.zza.getPackageManager().checkPermission(r1, r2);
    }

    public ApplicationInfo getApplicationInfo(String r1, int r2) {
        return this.zza.getPackageManager().getApplicationInfo(r1, r2);
    }

    public CharSequence getApplicationLabel(String r3) {
        Context r2 = this.zza;
        return r2.getPackageManager().getApplicationLabel(r2.getPackageManager().getApplicationInfo(r3, 0));
    }

    public PackageInfo getPackageInfo(String r1, int r2) {
        return this.zza.getPackageManager().getPackageInfo(r1, r2);
    }

    public boolean isCallerInstantApp() {
        if (Binder.getCallingUid() != Process.myUid()) goto L7;
        return InstantApps.isInstantApp(this.zza);
    L7:
        if (PlatformVersion.isAtLeastO() == false) goto L12;
        Context r2 = this.zza;
        String r0 = r2.getPackageManager().getNameForUid(Binder.getCallingUid());
        if (r0 != null) goto L11;
        return false;
    L11:
        return r2.getPackageManager().isInstantApp(r0);
    L12:
        return false;
    }

    public final boolean zza(int r2, String r3) {
        AppOpsManager r1 = (AppOpsManager) this.zza.getSystemService("appops");     // Catch: SecurityException -> L8
        if (r1 == null) goto L7;
        r1.checkPackage(r2, r3);     // Catch: SecurityException -> L8
        return true;
    L7:
        throw new NullPointerException("context.getSystemService(Context.APP_OPS_SERVICE) is null");     // Catch: SecurityException -> L8
    L8:
        return false;
    }
}
