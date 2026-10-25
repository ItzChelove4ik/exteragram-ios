package com.google.android.gms.auth.api.identity;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p000authapi.zbat;

/* loaded from: classes4.dex */
public abstract class Identity {
    public static SignInClient getSignInClient(Activity r2) {
        return new zbat((Activity) Preconditions.checkNotNull(r2), new zbv());
    }

    public static SignInClient getSignInClient(Context r2) {
        return new zbat((Context) Preconditions.checkNotNull(r2), new zbv());
    }
}
