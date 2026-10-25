package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zacn extends TaskApiCall {
    final /* synthetic */ TaskApiCall.Builder zaa;

    public zacn(TaskApiCall.Builder r1, Feature[] r2, boolean r3, int r4) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
        super(r2, r3, r4);
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public final void doExecute(Api.AnyClient r1, TaskCompletionSource r2) {
        this.zaa.zaa().accept(r1, r2);
    }
}
