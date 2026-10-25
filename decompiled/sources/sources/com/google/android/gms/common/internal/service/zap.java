package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zap implements RemoteCall {
    private final /* synthetic */ com.google.android.gms.common.internal.zab zaa;

    public /* synthetic */ zap(com.google.android.gms.common.internal.zab r1) {
        this.zaa = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r2, Object r3) {
        int r0 = zaq.$r8$clinit;
        ((zaj) ((zab) r2).getService()).zae(this.zaa);
        ((TaskCompletionSource) r3).setResult(null);
    }
}
