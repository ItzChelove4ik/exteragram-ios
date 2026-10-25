package com.google.android.gms.internal.p000authapi;

import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zbal extends zbk {
    final /* synthetic */ TaskCompletionSource zba;

    public zbal(zbat r1, TaskCompletionSource r2) {
        this.zba = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.p000authapi.zbl
    public final void zbb(Status r1, BeginSignInResult r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zba);
    }
}
