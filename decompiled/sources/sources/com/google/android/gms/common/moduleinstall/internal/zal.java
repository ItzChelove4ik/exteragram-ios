package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zal extends zaa {
    final /* synthetic */ TaskCompletionSource zaa;

    public zal(zay r1, TaskCompletionSource r2) {
        this.zaa = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.common.moduleinstall.internal.zaa, com.google.android.gms.common.moduleinstall.internal.zae
    public final void zac(Status r1, ModuleInstallResponse r2) {
        TaskUtil.trySetResultOrApiException(r1, r2, this.zaa);
    }
}
