package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaax extends com.google.android.gms.internal.base.zao {
    final /* synthetic */ zaaz zaa;

    public zaax(zaaz r1, Looper r2) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
        super(r2);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message r2) {
        int r22 = r2.what;
        if (r22 != 1) goto L5;
        this.zaa.zaj();
        return;
    L5:
        if (r22 == 2) goto L8;
        StringBuilder r0 = new StringBuilder(String.valueOf(r22).length() + 20);
        r0.append("Unknown message id: ");
        r0.append(r22);
        Log.w("GoogleApiClientImpl", r0.toString());
        return;
    L8:
        this.zaa.zai();
    }
}
