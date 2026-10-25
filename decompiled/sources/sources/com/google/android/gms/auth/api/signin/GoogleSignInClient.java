package com.google.android.gms.auth.api.signin;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.internal.zbm;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;

@Deprecated
/* loaded from: classes4.dex */
public class GoogleSignInClient extends GoogleApi<GoogleSignInOptions> {
    static int zba;
    private static final zba zbb = null;

    static {
        zbb = new zba(null);
        zba = 1;
    }

    public GoogleSignInClient(Context r4, GoogleSignInOptions r5) {
        super(r4, Auth.GOOGLE_SIGN_IN_API, r5, new GoogleApi.Settings.Builder().setMapper(new ApiExceptionMapper()).build());
    }

    private final synchronized int zba() {
        monitor-enter(this);
        int r0 = zba;     // Catch: Throwable -> L8
        if (r0 != 1) goto L16;
        Context r02 = getApplicationContext();     // Catch: Throwable -> L8
        GoogleApiAvailability r1 = GoogleApiAvailability.getInstance();     // Catch: Throwable -> L8
        int r2 = r1.isGooglePlayServicesAvailable(r02, 12451000);     // Catch: Throwable -> L8
        if (r2 != 0) goto L11;
        r0 = 4;
        zba = 4;     // Catch: Throwable -> L8
        goto L16
    L11:
        if (r1.getErrorResolutionIntent(r02, r2, null) == null) goto L13;
    L15:
        r0 = 2;
        zba = 2;     // Catch: Throwable -> L8
        goto L16
    L13:
        if (DynamiteModule.getLocalVersion(r02, "com.google.android.gms.auth.api.fallback") == 0) goto L15;
        r0 = 3;
        zba = 3;     // Catch: Throwable -> L8
    L16:
        monitor-exit(this);
        return r0;
    L8:
        th = move-exception;
        throw th;
    }

    public Intent getSignInIntent() {
        Context r0 = getApplicationContext();
        int r1 = zba();
        int r2 = r1 - 1;
        if (r1 == 0) goto L15;
        if (r2 == 2) goto L13;
        if (r2 == 3) goto L11;
        return zbm.zbc(r0, getApiOptions());
    L11:
        return zbm.zba(r0, getApiOptions());
    L13:
        return zbm.zbb(r0, getApiOptions());
    L15:
        throw null;
    }

    public Task<Void> revokeAccess() {
        GoogleApiClient r0 = asGoogleApiClient();
        Context r1 = getApplicationContext();
        if (zba() != 3) goto L5;
        boolean r3 = true;
    L7:
        return PendingResultUtil.toVoidTask(zbm.zbf(r0, r1, r3));
    L5:
        r3 = false;
        goto L7
    }

    public Task<Void> signOut() {
        GoogleApiClient r0 = asGoogleApiClient();
        Context r1 = getApplicationContext();
        if (zba() != 3) goto L5;
        boolean r3 = true;
    L7:
        return PendingResultUtil.toVoidTask(zbm.zbe(r0, r1, r3));
    L5:
        r3 = false;
        goto L7
    }
}
