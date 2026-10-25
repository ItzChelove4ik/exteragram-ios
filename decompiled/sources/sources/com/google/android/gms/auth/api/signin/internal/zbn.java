package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/* loaded from: classes4.dex */
public final class zbn {
    private static zbn zbd;
    final Storage zba;
    GoogleSignInAccount zbb;
    GoogleSignInOptions zbc;

    static {
    }

    private zbn(Context r2) {
        Storage r22 = Storage.getInstance(r2);
        this.zba = r22;
        this.zbb = r22.getSavedDefaultGoogleSignInAccount();
        this.zbc = r22.getSavedDefaultGoogleSignInOptions();
    }

    public static synchronized zbn zba(Context r1) {
        monitor-enter(zbn.class);
        zbn r12 = zbf(r1.getApplicationContext());     // Catch: Throwable -> L7
        monitor-exit(zbn.class);
        return r12;
    L7:
        th = move-exception;
        throw th;
    }

    private static synchronized zbn zbf(Context r2) {
        monitor-enter(zbn.class);
        zbn r1 = zbd;     // Catch: Throwable -> L11
        if (r1 == null) goto L8;
        monitor-exit(zbn.class);
        return r1;
    L8:
        zbn r12 = new zbn(r2);     // Catch: Throwable -> L11
        zbd = r12;     // Catch: Throwable -> L11
        monitor-exit(zbn.class);
        return r12;
    L11:
        th = move-exception;
        throw th;
    }

    public final synchronized void zbb() {
        monitor-enter(this);
        this.zba.clear();     // Catch: Throwable -> L6
        this.zbb = null;     // Catch: Throwable -> L6
        this.zbc = null;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized void zbc(GoogleSignInOptions r2, GoogleSignInAccount r3) {
        monitor-enter(this);
        this.zba.saveDefaultGoogleSignInAccount(r3, r2);     // Catch: Throwable -> L6
        this.zbb = r3;     // Catch: Throwable -> L6
        this.zbc = r2;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
