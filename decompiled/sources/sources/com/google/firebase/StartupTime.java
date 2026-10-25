package com.google.firebase;

import android.os.SystemClock;

/* loaded from: classes.dex */
public abstract class StartupTime {
    public StartupTime() {
    }

    public static StartupTime create(long r7, long r9, long r11) {
        return new AutoValue_StartupTime(r7, r9, r11);
    }

    public static StartupTime now() {
        return create(System.currentTimeMillis(), SystemClock.elapsedRealtime(), SystemClock.uptimeMillis());
    }

    public abstract long getElapsedRealtime();

    public abstract long getEpochMillis();

    public abstract long getUptimeMillis();
}
