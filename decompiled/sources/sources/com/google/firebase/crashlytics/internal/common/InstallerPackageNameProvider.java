package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
class InstallerPackageNameProvider {
    private String installerPackageName;

    public InstallerPackageNameProvider() {
    }

    private static String loadInstallerPackageName(Context r1) {
        String r12 = r1.getPackageManager().getInstallerPackageName(r1.getPackageName());
        if (r12 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return r12;
    }

    public synchronized String getInstallerPackageName(Context r2) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.installerPackageName != null) goto L9;
        this.installerPackageName = loadInstallerPackageName(r2);     // Catch: Throwable -> L6
    L9:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(this.installerPackageName) == false) goto L11;
        String r22 = null;
    L12:
        monitor-exit(this);
        return r22;
    L11:
        r22 = this.installerPackageName;     // Catch: Throwable -> L6
        goto L12
    }
}
