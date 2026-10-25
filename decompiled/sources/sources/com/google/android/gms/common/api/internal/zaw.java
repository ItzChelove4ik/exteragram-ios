package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaw implements zabu {
    final /* synthetic */ zax zaa;

    public /* synthetic */ zaw(zax r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // com.google.android.gms.common.api.internal.zabu
    public final void zaa(Bundle r2) {
        zax r22 = this.zaa;
        r22.zay().lock();
        r22.zav(ConnectionResult.RESULT_SUCCESS);     // Catch: Throwable -> L6
        r22.zao();     // Catch: Throwable -> L6
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
        r0.zav(r3);     // Catch: Throwable -> L6
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
    L6:
        th = move-exception;
        this.zaa.zay().unlock();
        throw th;
    L4:
        if (r0.zaw() == false) goto L8;
        r0.zax(false);     // Catch: Throwable -> L6
        r0.zap(r3, r4);     // Catch: Throwable -> L6
    L9:
        r0.zay().unlock();
        return;
    L8:
        r0.zax(true);     // Catch: Throwable -> L6
        r0.zar().onConnectionSuspended(r3);     // Catch: Throwable -> L6
        goto L9
    }
}
