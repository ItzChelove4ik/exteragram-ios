package com.google.android.gms.dynamic;

import android.os.Bundle;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zac implements zah {
    final /* synthetic */ Bundle zaa;
    final /* synthetic */ DeferredLifecycleHelper zab;

    public zac(DeferredLifecycleHelper r1, Bundle r2) {
        this.zaa = r2;
        Objects.requireNonNull(r1);
        this.zab = r1;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final int zaa() {
        return 1;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final void zab(LifecycleDelegate r1) {
        this.zab.zaa().onCreate(this.zaa);
    }
}
