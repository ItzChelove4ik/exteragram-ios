package com.google.android.gms.internal.wallet;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes5.dex */
public final class zzd extends Handler {
    private final Looper zza;

    public zzd(Looper r1) {
        super(r1);
        this.zza = Looper.getMainLooper();
    }
}
