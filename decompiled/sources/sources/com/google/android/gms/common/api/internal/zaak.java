package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaak extends zabb {
    final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks zaa;

    public zaak(zaal r1, zaba r2, BaseGmsClient.ConnectionProgressReportCallbacks r3) {
        this.zaa = r3;
        Objects.requireNonNull(r1);
        super(r2);
    }

    @Override // com.google.android.gms.common.api.internal.zabb
    public final void zaa() {
        ConnectionResult r0 = new ConnectionResult(16, null);
        this.zaa.onReportServiceBinding(r0);
    }
}
