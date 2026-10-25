package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zabj implements BaseGmsClient.SignOutCallbacks {
    final /* synthetic */ zabk zaa;

    public zabj(zabk r1) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks
    public final void onSignOutComplete() {
        zabi r0 = new zabi(this);
        this.zaa.zaa.zaF().post(r0);
    }
}
