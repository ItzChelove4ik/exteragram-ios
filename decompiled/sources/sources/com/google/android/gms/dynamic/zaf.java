package com.google.android.gms.dynamic;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zaf implements zah {
    final /* synthetic */ DeferredLifecycleHelper zaa;

    public zaf(DeferredLifecycleHelper r1) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final int zaa() {
        return 4;
    }

    @Override // com.google.android.gms.dynamic.zah
    public final void zab(LifecycleDelegate r1) {
        this.zaa.zaa().onStart();
    }
}
