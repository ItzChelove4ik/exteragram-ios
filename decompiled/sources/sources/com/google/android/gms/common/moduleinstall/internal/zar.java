package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zar implements RemoteCall {
    private final /* synthetic */ zay zaa;
    private final /* synthetic */ ApiFeatureRequest zab;

    public /* synthetic */ zar(zay r1, ApiFeatureRequest r2) {
        this.zaa = r1;
        this.zab = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r3, Object r4) {
        zal r1 = new zal(this.zaa, (TaskCompletionSource) r4);
        ((zaf) ((zaz) r3).getService()).zaf(r1, this.zab, null);
    }
}
