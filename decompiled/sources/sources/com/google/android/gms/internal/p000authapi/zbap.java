package com.google.android.gms.internal.p000authapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zbap implements RemoteCall {
    private final /* synthetic */ zbat zba;

    public /* synthetic */ zbap(zbat r1) {
        this.zba = r1;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r1, Object r2) {
        zbat r0 = this.zba;
        zbau r12 = (zbau) r1;
        r0.zba(r12, (TaskCompletionSource) r2);
    }
}
