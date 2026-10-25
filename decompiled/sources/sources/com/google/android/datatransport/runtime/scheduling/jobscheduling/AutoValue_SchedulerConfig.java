package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Map;

/* loaded from: classes.dex */
final class AutoValue_SchedulerConfig extends SchedulerConfig {
    private final Clock clock;
    private final Map<Priority, SchedulerConfig.ConfigValue> values;

    public AutoValue_SchedulerConfig(Clock r2, Map<Priority, SchedulerConfig.ConfigValue> r3) {
        if (r2 == null) goto L10;
        this.clock = r2;
        if (r3 == null) goto L8;
        this.values = r3;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null values");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null clock");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SchedulerConfig) == false) goto L12;
        SchedulerConfig r52 = (SchedulerConfig) r5;
        if (this.clock.equals(r52.getClock()) == false) goto L12;
        if (this.values.equals(r52.getValues()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
    public Clock getClock() {
        return this.clock;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
    public Map<Priority, SchedulerConfig.ConfigValue> getValues() {
        return this.values;
    }

    public int hashCode() {
        int r0 = (this.clock.hashCode() ^ 1000003) * 1000003;
        return this.values.hashCode() ^ r0;
    }

    public String toString() {
        return "SchedulerConfig{clock=" + this.clock + ", values=" + this.values + "}";
    }
}
