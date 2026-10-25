package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.Objects;
import java.util.concurrent.locks.Lock;

/* loaded from: classes4.dex */
final class zav implements zabu {
    final /* synthetic */ zax zaa;

    public /* synthetic */ zav(zax r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // com.google.android.gms.common.api.internal.zabu
    public final void zaa(Bundle r3) {
        zax r0 = this.zaa;
        r0.zay().lock();
        r0.zaq(r3);     // Catch: Throwable -> L6
        r0.zat(ConnectionResult.RESULT_SUCCESS);     // Catch: Throwable -> L6
        r0.zao();     // Catch: Throwable -> L6
        this.zaa.zay().unlock();
        return;
    L6:
        th = move-exception;
        this.zaa.zay().unlock();
        throw th;
    }

    @Override // com.google.android.gms.common.api.internal.zabu
    public final void zab(ConnectionResult r3) {
        zax r0 = this.zaa;
        r0.zay().lock();
        r0.zat(r3);     // Catch: Throwable -> L6
        r0.zao();     // Catch: Throwable -> L6
        this.zaa.zay().unlock();
        return;
    L6:
        th = move-exception;
        this.zaa.zay().unlock();
        throw th;
    }

    @Override // com.google.android.gms.common.api.internal.zabu
    public final void zac(int r3, boolean r4) {
        zax r0 = this.zaa;
        r0.zay().lock();
    L14:
        th = move-exception;
        this.zaa.zay().unlock();
        throw th;
    L4:
        if (r0.zaw() == true) goto L17;
        if (r0.zau() == null) goto L17;
        if (r0.zau().isSuccess() == false) goto L17;
        r0.zax(true);     // Catch: Throwable -> L14
        r0.zas().onConnectionSuspended(r3);     // Catch: Throwable -> L14
        Lock r2 = this.zaa.zay();
    L12:
        r2.unlock();
        return;
    L17:
        r0.zax(false);     // Catch: Throwable -> L14
        r0.zap(r3, r4);     // Catch: Throwable -> L14
        r2 = r0.zay();
        goto L12
    }
}
