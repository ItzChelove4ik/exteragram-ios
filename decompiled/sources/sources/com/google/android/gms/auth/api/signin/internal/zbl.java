package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;

/* loaded from: classes4.dex */
abstract class zbl extends BaseImplementation$ApiMethodImpl {
    public zbl(GoogleApiClient r2) {
        super(Auth.GOOGLE_SIGN_IN_API, r2);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl, com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder
    public final /* bridge */ /* synthetic */ void setResult(Object r1) {
        super.setResult((Result) r1);
    }
}
