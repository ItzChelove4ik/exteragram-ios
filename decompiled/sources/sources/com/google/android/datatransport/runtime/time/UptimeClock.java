package com.google.android.datatransport.runtime.time;

import android.os.SystemClock;

/* loaded from: classes.dex */
public class UptimeClock implements Clock {
    public UptimeClock() {
    }

    @Override // com.google.android.datatransport.runtime.time.Clock
    public long getTime() {
        return SystemClock.elapsedRealtime();
    }
}
