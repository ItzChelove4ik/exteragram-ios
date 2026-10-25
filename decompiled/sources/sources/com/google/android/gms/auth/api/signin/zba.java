package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* loaded from: classes4.dex */
final class zba implements PendingResultUtil.ResultConverter {
    public /* synthetic */ zba(byte[] r1) {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* synthetic */ Object convert(Result r1) {
        return ((GoogleSignInResult) r1).getSignInAccount();
    }
}
