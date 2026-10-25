package com.google.android.gms.internal.wearable;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes5.dex */
public abstract class zzi extends Handler {
    public zzi(Looper r1) {
        super(r1);
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message r1) {
        zza(r1);
    }

    public void zza(Message r1) {
        super.dispatchMessage(r1);
    }
}
