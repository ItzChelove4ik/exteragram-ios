package com.google.android.gms.common.api.internal;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zau implements Runnable {
    final /* synthetic */ zax zaa;

    public zau(zax r1) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zax r0 = this.zaa;
        r0.zay().lock();
        r0.zao();     // Catch: Throwable -> L6
        this.zaa.zay().unlock();
        return;
    L6:
        th = move-exception;
        this.zaa.zay().unlock();
        throw th;
    }
}
