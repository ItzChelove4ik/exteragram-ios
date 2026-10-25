package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
final class zaay extends zabr {
    private final WeakReference zaa;

    public zaay(zaaz r2) {
        this.zaa = new WeakReference(r2);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void zaa() {
        zaaz r0 = (zaaz) this.zaa.get();
        if (r0 != null) goto L5;
        return;
    L5:
        r0.zai();
    }
}
