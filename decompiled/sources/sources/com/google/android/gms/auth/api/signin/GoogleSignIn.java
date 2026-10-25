package com.google.android.gms.auth.api.signin;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.signin.internal.zbm;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

@Deprecated
/* loaded from: classes4.dex */
public abstract class GoogleSignIn {
    public static GoogleSignInClient getClient(Context r1, GoogleSignInOptions r2) {
        return new GoogleSignInClient(r1, (GoogleSignInOptions) Preconditions.checkNotNull(r2));
    }

    public static Task<GoogleSignInAccount> getSignedInAccountFromIntent(Intent r2) {
        GoogleSignInResult r22 = zbm.zbg(r2);
        GoogleSignInAccount r0 = r22.getSignInAccount();
        if (r22.getStatus().isSuccess() == false) goto L9;
        if (r0 == null) goto L9;
        return Tasks.forResult(r0);
    L9:
        return Tasks.forException(ApiExceptionUtil.fromStatus(r22.getStatus()));
    }
}
