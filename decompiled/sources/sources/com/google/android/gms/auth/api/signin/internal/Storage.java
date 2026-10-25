package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes4.dex */
public class Storage {
    private static final Lock zaa = null;
    private static Storage zab;
    private final Lock zac;
    private final SharedPreferences zad;

    static {
        zaa = new ReentrantLock();
    }

    public Storage(Context r3) {
        this.zac = new ReentrantLock();
        this.zad = r3.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static Storage getInstance(Context r2) {
        Preconditions.checkNotNull(r2);
        Lock r0 = zaa;
        r0.lock();
    L9:
        th = move-exception;
        zaa.unlock();
        throw th;
    L4:
        if (zab != null) goto L6;
        zab = new Storage(r2.getApplicationContext());     // Catch: Throwable -> L9
    L6:
        Storage r22 = zab;     // Catch: Throwable -> L9
        r0.unlock();
        return r22;
    }

    private static final String zae(String r3, String r4) {
        int r0 = String.valueOf(r4).length();
        StringBuilder r2 = new StringBuilder((r3.length() + 1) + r0);
        r2.append(r3);
        r2.append(":");
        r2.append(r4);
        return r2.toString();
    }

    public void clear() {
        this.zac.lock();
        this.zad.edit().clear().apply();     // Catch: Throwable -> L6
        this.zac.unlock();
        return;
    L6:
        th = move-exception;
        this.zac.unlock();
        throw th;
    }

    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        String r0 = zab("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(r0) == false) goto L5;
        return null;
    L5:
        String r3 = zab(zae("googleSignInAccount", r0));
        if (r3 != null) goto L11;
    L9:
        return null;
    L11:
        return GoogleSignInAccount.zaa(r3);
    }

    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        String r0 = zab("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(r0) == false) goto L5;
        return null;
    L5:
        String r3 = zab(zae("googleSignInOptions", r0));
        if (r3 != null) goto L11;
    L9:
        return null;
    L11:
        return GoogleSignInOptions.zaa(r3);
    }

    public String getSavedRefreshToken() {
        return zab("refreshToken");
    }

    public void saveDefaultGoogleSignInAccount(GoogleSignInAccount r3, GoogleSignInOptions r4) {
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(r4);
        zaa("defaultGoogleSignInAccount", r3.zac());
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(r4);
        String r0 = r3.zac();
        zaa(zae("googleSignInAccount", r0), r3.zad());
        zaa(zae("googleSignInOptions", r0), r4.zab());
    }

    public final void zaa(String r2, String r3) {
        this.zac.lock();
        this.zad.edit().putString(r2, r3).apply();     // Catch: Throwable -> L6
        this.zac.unlock();
        return;
    L6:
        th = move-exception;
        this.zac.unlock();
        throw th;
    }

    public final String zab(String r3) {
        this.zac.lock();
        String r32 = this.zad.getString(r3, null);     // Catch: Throwable -> L6
        this.zac.unlock();
        return r32;
    L6:
        th = move-exception;
        this.zac.unlock();
        throw th;
    }
}
