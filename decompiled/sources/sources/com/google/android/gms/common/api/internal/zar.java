package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zar {
    final /* synthetic */ BasePendingResult zaa;

    public /* synthetic */ zar(BasePendingResult r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    public final void finalize() {
        BasePendingResult.zal(this.zaa.zam());
        super.finalize();
    }
}
