package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zaat implements com.google.android.gms.common.internal.zam {
    final /* synthetic */ zaaz zaa;

    public zaat(zaaz r1) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // com.google.android.gms.common.internal.zam
    public final boolean isConnected() {
        return this.zaa.isConnected();
    }
}
