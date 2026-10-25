package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
final class zaai implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final WeakReference zaa;
    private final Api zab;
    private final boolean zac;

    public zaai(zaar r2, Api r3, boolean r4) {
        this.zaa = new WeakReference(r2);
        this.zab = r3;
        this.zac = r4;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(ConnectionResult r5) {
        zaar r0 = (zaar) this.zaa.get();
        if (r0 != null) goto L6;
        return;
    L6:
        if (Looper.myLooper() != r0.zar().zag.getLooper()) goto L8;
        boolean r1 = true;
    L9:
        Preconditions.checkState(r1, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        r0.zas().lock();
    L16:
        th = move-exception;
        r0.zas().unlock();
        throw th;
    L11:
        if (r0.zaq(0) == true) goto L14;
    L21:
        r0.zas().unlock();
        return;
    L14:
        if (r5.isSuccess() == true) goto L19;
        r0.zal(r5, this.zab, this.zac);     // Catch: Throwable -> L16
    L19:
        if (r0.zai() == false) goto L21;
        r0.zak();     // Catch: Throwable -> L16
        goto L21
    L8:
        r1 = false;
        goto L9
    }

    public final /* synthetic */ boolean zaa() {
        return this.zac;
    }
}
