package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import java.util.Set;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
final class AutoValue_SchedulerConfig_ConfigValue extends SchedulerConfig.ConfigValue {
    private final long delta;
    private final Set<SchedulerConfig.Flag> flags;
    private final long maxAllowedDelay;

    /* renamed from: com.google.android.datatransport.runtime.scheduling.jobscheduling.AutoValue_SchedulerConfig_ConfigValue$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends SchedulerConfig.ConfigValue.Builder {
        private Long delta;
        private Set<SchedulerConfig.Flag> flags;
        private Long maxAllowedDelay;

        public Builder() {
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue build() {
            if (this.delta != null) goto L5;
            String r0 = " delta";
        L7:
            if (this.maxAllowedDelay != null) goto L10;
            r0 = r0.concat(" maxAllowedDelay");
        L10:
            if (this.flags != null) goto L13;
            r0 = r0.concat(" flags");
        L13:
            if (r0.isEmpty() == true) goto L15;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0));
            return null;
        L15:
            return new AutoValue_SchedulerConfig_ConfigValue(this.delta.longValue(), this.maxAllowedDelay.longValue(), this.flags, null);
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue.Builder setDelta(long r1) {
            this.delta = Long.valueOf(r1);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue.Builder setFlags(Set<SchedulerConfig.Flag> r1) {
            if (r1 == null) goto L5;
            this.flags = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null flags");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue.Builder setMaxAllowedDelay(long r1) {
            this.maxAllowedDelay = Long.valueOf(r1);
            return this;
        }
    }

    private AutoValue_SchedulerConfig_ConfigValue(long r1, long r3, Set<SchedulerConfig.Flag> r5) {
        this.delta = r1;
        this.maxAllowedDelay = r3;
        this.flags = r5;
    }

    public /* synthetic */ AutoValue_SchedulerConfig_ConfigValue(long r1, long r3, Set r5, AnonymousClass1 r6) {
        this(r1, r3, r5);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof SchedulerConfig.ConfigValue) == false) goto L14;
        SchedulerConfig.ConfigValue r82 = (SchedulerConfig.ConfigValue) r8;
        if (this.delta != r82.getDelta()) goto L14;
        if (this.maxAllowedDelay != r82.getMaxAllowedDelay()) goto L14;
        if (this.flags.equals(r82.getFlags()) == false) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue
    public long getDelta() {
        return this.delta;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue
    public Set<SchedulerConfig.Flag> getFlags() {
        return this.flags;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue
    public long getMaxAllowedDelay() {
        return this.maxAllowedDelay;
    }

    public int hashCode() {
        long r0 = this.delta;
        int r02 = (((int) (r0 ^ (r0 >>> 32))) ^ 1000003) * 1000003;
        long r3 = this.maxAllowedDelay;
        int r7 = this.flags.hashCode();
        return r7 ^ ((r02 ^ ((int) ((r3 >>> 32) ^ r3))) * 1000003);
    }

    public String toString() {
        return "ConfigValue{delta=" + this.delta + ", maxAllowedDelay=" + this.maxAllowedDelay + ", flags=" + this.flags + "}";
    }
}
