package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zacj implements Runnable {
    final /* synthetic */ zacm zaa;

    public zacj(zacm r1) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConnectionResult r0 = new ConnectionResult(4);
        this.zaa.zaf().zaa(r0);
    }
}
