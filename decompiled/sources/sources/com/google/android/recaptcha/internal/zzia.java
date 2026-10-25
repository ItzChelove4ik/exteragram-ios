package com.google.android.recaptcha.internal;

import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
final class zzia implements Executor {
    public static final zzia zza = null;

    static {
        zza = new zzia();
    }

    private zzia() {
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        r1.run();
    }
}
