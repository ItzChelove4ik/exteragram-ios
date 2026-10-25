package com.google.android.recaptcha.internal;

import java.util.TimerTask;

/* loaded from: classes5.dex */
public final class zzlu extends TimerTask {
    final /* synthetic */ zzlx zza;

    public zzlu(zzlx r1) {
        this.zza = r1;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        zzlx.zze(this.zza, 0);     // Catch: Exception -> L4
        return;
    }
}
