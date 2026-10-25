package com.google.android.gms.internal.common;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes4.dex */
public class zzg extends Handler {
    private final Looper zza;

    public zzg(Looper r1) {
        super(r1);
        this.zza = Looper.getMainLooper();
    }

    public zzg(Looper r1, Handler.Callback r2) {
        super(r1, r2);
        this.zza = Looper.getMainLooper();
    }
}
