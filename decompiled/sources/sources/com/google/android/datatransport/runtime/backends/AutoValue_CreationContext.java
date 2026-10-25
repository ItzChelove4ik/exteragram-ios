package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.datatransport.runtime.time.Clock;

/* loaded from: classes4.dex */
final class AutoValue_CreationContext extends CreationContext {
    private final Context applicationContext;
    private final String backendName;
    private final Clock monotonicClock;
    private final Clock wallClock;

    public AutoValue_CreationContext(Context r2, Clock r3, Clock r4, String r5) {
        if (r2 == null) goto L18;
        this.applicationContext = r2;
        if (r3 == null) goto L16;
        this.wallClock = r3;
        if (r4 == null) goto L14;
        this.monotonicClock = r4;
        if (r5 == null) goto L12;
        this.backendName = r5;
        return;
    L12:
        f$$ExternalSyntheticBUOutline2.m("Null backendName");
        throw null;
    L14:
        f$$ExternalSyntheticBUOutline2.m("Null monotonicClock");
        throw null;
    L16:
        f$$ExternalSyntheticBUOutline2.m("Null wallClock");
        throw null;
    L18:
        f$$ExternalSyntheticBUOutline2.m("Null applicationContext");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof CreationContext) == false) goto L16;
        CreationContext r52 = (CreationContext) r5;
        if (this.applicationContext.equals(r52.getApplicationContext()) == false) goto L16;
        if (this.wallClock.equals(r52.getWallClock()) == false) goto L16;
        if (this.monotonicClock.equals(r52.getMonotonicClock()) == false) goto L16;
        if (this.backendName.equals(r52.getBackendName()) == false) goto L16;
        return true;
    L16:
        return false;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public Context getApplicationContext() {
        return this.applicationContext;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public String getBackendName() {
        return this.backendName;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public Clock getMonotonicClock() {
        return this.monotonicClock;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public Clock getWallClock() {
        return this.wallClock;
    }

    public int hashCode() {
        int r0 = (((((this.applicationContext.hashCode() ^ 1000003) * 1000003) ^ this.wallClock.hashCode()) * 1000003) ^ this.monotonicClock.hashCode()) * 1000003;
        return this.backendName.hashCode() ^ r0;
    }

    public String toString() {
        return "CreationContext{applicationContext=" + this.applicationContext + ", wallClock=" + this.wallClock + ", monotonicClock=" + this.monotonicClock + ", backendName=" + this.backendName + "}";
    }
}
