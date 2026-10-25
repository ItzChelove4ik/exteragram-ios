package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.logging.Logger;
import java.util.Iterator;

/* loaded from: classes4.dex */
public abstract class zbm {
    private static final Logger zba = null;

    static {
        zba = new Logger("GoogleSignInCommon", new String[0]);
    }

    public static Intent zba(Context r3, GoogleSignInOptions r4) {
        zba.d("getSignInIntent()", new Object[0]);
        SignInConfiguration r0 = new SignInConfiguration(r3.getPackageName(), r4);
        Intent r42 = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        r42.setPackage(r3.getPackageName());
        r42.setClass(r3, SignInHubActivity.class);
        Bundle r32 = new Bundle();
        r32.putParcelable("config", r0);
        r42.putExtra("config", r32);
        return r42;
    }

    public static Intent zbb(Context r3, GoogleSignInOptions r4) {
        zba.d("getFallbackSignInIntent()", new Object[0]);
        Intent r32 = zba(r3, r4);
        r32.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return r32;
    }

    public static Intent zbc(Context r3, GoogleSignInOptions r4) {
        zba.d("getNoImplementationSignInIntent()", new Object[0]);
        Intent r32 = zba(r3, r4);
        r32.setAction("com.google.android.gms.auth.NO_IMPL");
        return r32;
    }

    public static PendingResult zbe(GoogleApiClient r3, Context r4, boolean r5) {
        zba.d("Signing out", new Object[0]);
        zbh(r4);
        if (r5 == false) goto L7;
        return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, r3);
    L7:
        return r3.execute(new zbi(r3));
    }

    public static PendingResult zbf(GoogleApiClient r3, Context r4, boolean r5) {
        zba.d("Revoking access", new Object[0]);
        String r0 = Storage.getInstance(r4).getSavedRefreshToken();
        zbh(r4);
        if (r5 == false) goto L7;
        return zbb.zba(r0);
    L7:
        return r3.execute(new zbk(r3));
    }

    public static GoogleSignInResult zbg(Intent r3) {
        if (r3 == null) goto L5;
        Status r1 = (Status) r3.getParcelableExtra("googleSignInStatus");
        GoogleSignInAccount r32 = (GoogleSignInAccount) r3.getParcelableExtra("googleSignInAccount");
        if (r32 != null) goto L14;
        if (r1 != null) goto L12;
        r1 = Status.RESULT_INTERNAL_ERROR;
    L12:
        return new GoogleSignInResult(null, r1);
    L14:
        return new GoogleSignInResult(r32, Status.RESULT_SUCCESS);
    L5:
        return new GoogleSignInResult(null, Status.RESULT_INTERNAL_ERROR);
    }

    private static void zbh(Context r1) {
        zbn.zba(r1).zbb();
        Iterator<GoogleApiClient> r12 = GoogleApiClient.getAllClients().iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        r12.next().maybeSignOut();
        goto L4
    L6:
        GoogleApiManager.reportSignOut();
    }
}
