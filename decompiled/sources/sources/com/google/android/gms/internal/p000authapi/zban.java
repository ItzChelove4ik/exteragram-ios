package com.google.android.gms.internal.p000authapi;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zban extends zbp {
    final /* synthetic */ TaskCompletionSource zba;

    public zban(zbat r1, TaskCompletionSource r2) {
        this.zba = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.p000authapi.zbq
    public final void zbb(Status r1, PendingIntent r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zba);
    }
}
