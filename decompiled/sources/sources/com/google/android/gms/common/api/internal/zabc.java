package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zabc extends com.google.android.gms.internal.base.zao {
    final /* synthetic */ zabd zaa;

    public zabc(zabd r1, Looper r2) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
        super(r2);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message r3) {
        int r0 = r3.what;
        if (r0 != 1) goto L5;
        ((zabb) r3.obj).zab(this.zaa);
        return;
    L5:
        if (r0 == 2) goto L9;
        StringBuilder r32 = new StringBuilder(String.valueOf(r0).length() + 20);
        r32.append("Unknown message id: ");
        r32.append(r0);
        Log.w("GACStateManager", r32.toString());
        return;
    L9:
        throw ((RuntimeException) r3.obj);
    }
}
