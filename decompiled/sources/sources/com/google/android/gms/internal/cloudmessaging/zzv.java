package com.google.android.gms.internal.cloudmessaging;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes4.dex */
public class zzv extends Handler {
    public zzv(Looper r1) {
        super(r1);
        Looper.getMainLooper();
    }

    public zzv(Looper r1, Handler.Callback r2) {
        super(r1, r2);
        Looper.getMainLooper();
    }
}
