package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaj extends zaa {
    final /* synthetic */ TaskCompletionSource zaa;

    public zaj(zay r1, TaskCompletionSource r2) {
        this.zaa = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.common.moduleinstall.internal.zaa, com.google.android.gms.common.moduleinstall.internal.zae
    public final void zab(Status r1, ModuleAvailabilityResponse r2) {
        TaskUtil.trySetResultOrApiException(r1, r2, this.zaa);
    }
}
