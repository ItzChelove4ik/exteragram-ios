package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zas implements RemoteCall {
    private final /* synthetic */ TelemetryData zaa;

    public /* synthetic */ zas(TelemetryData r1) {
        this.zaa = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r2, Object r3) {
        int r0 = zat.$r8$clinit;
        ((zak) ((zau) r2).getService()).zae(this.zaa);
        ((TaskCompletionSource) r3).setResult(null);
    }
}
