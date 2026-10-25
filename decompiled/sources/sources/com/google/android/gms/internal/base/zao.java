package com.google.android.gms.internal.base;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes4.dex */
public class zao extends Handler {
    private final Looper zaa;

    public zao(Looper r1) {
        super(r1);
        this.zaa = Looper.getMainLooper();
    }

    public zao(Looper r1, Handler.Callback r2) {
        super(r1, r2);
        this.zaa = Looper.getMainLooper();
    }
}
