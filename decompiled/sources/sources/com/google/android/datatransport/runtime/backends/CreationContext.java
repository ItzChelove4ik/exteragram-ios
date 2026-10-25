package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;

/* loaded from: classes4.dex */
public abstract class CreationContext {
    public CreationContext() {
    }

    public static CreationContext create(Context r1, Clock r2, Clock r3, String r4) {
        return new AutoValue_CreationContext(r1, r2, r3, r4);
    }

    public abstract Context getApplicationContext();

    public abstract String getBackendName();

    public abstract Clock getMonotonicClock();

    public abstract Clock getWallClock();
}
