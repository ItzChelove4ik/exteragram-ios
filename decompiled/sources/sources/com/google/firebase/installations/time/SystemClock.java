package com.google.firebase.installations.time;

/* loaded from: classes.dex */
public class SystemClock implements Clock {
    private static SystemClock singleton;

    private SystemClock() {
    }

    public static SystemClock getInstance() {
        if (singleton != null) goto L6;
        singleton = new SystemClock();
    L6:
        return singleton;
    }

    @Override // com.google.firebase.installations.time.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}
