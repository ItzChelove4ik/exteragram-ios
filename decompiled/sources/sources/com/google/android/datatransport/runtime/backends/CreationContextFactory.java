package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;

/* loaded from: classes.dex */
class CreationContextFactory {
    private final Context applicationContext;
    private final Clock monotonicClock;
    private final Clock wallClock;

    public CreationContextFactory(Context r1, Clock r2, Clock r3) {
        this.applicationContext = r1;
        this.wallClock = r2;
        this.monotonicClock = r3;
    }

    public CreationContext create(String r3) {
        return CreationContext.create(this.applicationContext, this.wallClock, this.monotonicClock, r3);
    }
}
