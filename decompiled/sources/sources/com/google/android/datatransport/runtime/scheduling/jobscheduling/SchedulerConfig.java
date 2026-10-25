package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobInfo;
import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.AutoValue_SchedulerConfig_ConfigValue;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.time.DurationKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class SchedulerConfig {

    public static class Builder {
        private Clock clock;
        private Map<Priority, ConfigValue> values;

        public Builder() {
            this.values = new HashMap();
        }

        public Builder addConfig(Priority r2, ConfigValue r3) {
            this.values.put(r2, r3);
            return this;
        }

        public SchedulerConfig build() {
            if (this.clock != null) goto L5;
            f$$ExternalSyntheticBUOutline2.m("missing required property: clock");
            return null;
        L5:
            if (this.values.keySet().size() < Priority.values().length) goto L8;
            Map<Priority, ConfigValue> r0 = this.values;
            this.values = new HashMap();
            return SchedulerConfig.create(this.clock, r0);
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("Not all priorities have been configured");
            return null;
        }

        public Builder setClock(Clock r1) {
            this.clock = r1;
            return this;
        }
    }

    public static abstract class ConfigValue {

        public static abstract class Builder {
            public Builder() {
            }

            public abstract ConfigValue build();

            public abstract Builder setDelta(long r1);

            public abstract Builder setFlags(Set<Flag> r1);

            public abstract Builder setMaxAllowedDelay(long r1);
        }

        public ConfigValue() {
        }

        public static Builder builder() {
            return new AutoValue_SchedulerConfig_ConfigValue.Builder().setFlags(Collections.EMPTY_SET);
        }

        public abstract long getDelta();

        public abstract Set<Flag> getFlags();

        public abstract long getMaxAllowedDelay();
    }

    public enum Flag extends Enum<Flag> {
        private static final /* synthetic */ Flag[] $VALUES = null;
        public static final Flag DEVICE_CHARGING = null;
        public static final Flag DEVICE_IDLE = null;
        public static final Flag NETWORK_UNMETERED = null;

        static {
            Flag r0 = new Flag("NETWORK_UNMETERED", 0);
            NETWORK_UNMETERED = r0;
            Flag r1 = new Flag("DEVICE_IDLE", 1);
            DEVICE_IDLE = r1;
            Flag r2 = new Flag("DEVICE_CHARGING", 2);
            DEVICE_CHARGING = r2;
            $VALUES = new Flag[]{r0, r1, r2};
        }

        Flag(String r1, int r2) {
        }

        public static Flag valueOf(String r1) {
            return (Flag) Enum.valueOf(Flag.class, r1);
        }

        public static Flag[] values() {
            return (Flag[]) $VALUES.clone();
        }
    }

    public SchedulerConfig() {
    }

    private long adjustedExponentialBackoff(int r7, long r8) {
        int r72 = r7 - 1;
        if (r8 <= 1) goto L5;
        long r0 = r8;
    L7:
        return (long) ((Math.pow(3.0d, r72) * r8) * Math.max(1.0d, Math.log(10000.0d) / Math.log(r0 * r72)));
    L5:
        r0 = 2;
        goto L7
    }

    public static Builder builder() {
        return new Builder();
    }

    public static SchedulerConfig create(Clock r1, Map<Priority, ConfigValue> r2) {
        return new AutoValue_SchedulerConfig(r1, r2);
    }

    public static SchedulerConfig getDefault(Clock r7) {
        return builder().addConfig(Priority.DEFAULT, ConfigValue.builder().setDelta(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS).setMaxAllowedDelay(DurationKt.MILLIS_IN_DAY).build()).addConfig(Priority.HIGHEST, ConfigValue.builder().setDelta(1000).setMaxAllowedDelay(DurationKt.MILLIS_IN_DAY).build()).addConfig(Priority.VERY_LOW, ConfigValue.builder().setDelta(DurationKt.MILLIS_IN_DAY).setMaxAllowedDelay(DurationKt.MILLIS_IN_DAY).setFlags(immutableSetOf(new Flag[]{Flag.DEVICE_IDLE})).build()).setClock(r7).build();
    }

    private static <T> Set<T> immutableSetOf(T... r1) {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(r1)));
    }

    private void populateFlags(JobInfo.Builder r2, Set<Flag> r3) {
        if (r3.contains(Flag.NETWORK_UNMETERED) == false) goto L5;
        r2.setRequiredNetworkType(2);
    L7:
        if (r3.contains(Flag.DEVICE_CHARGING) == false) goto L10;
        r2.setRequiresCharging(true);
    L10:
        if (r3.contains(Flag.DEVICE_IDLE) == false) goto L13;
        r2.setRequiresDeviceIdle(true);
        return;
    L13:
        return;
    L5:
        r2.setRequiredNetworkType(1);
        goto L7
    }

    public JobInfo.Builder configureJob(JobInfo.Builder r1, Priority r2, long r3, int r5) {
        r1.setMinimumLatency(getScheduleDelay(r2, r3, r5));
        populateFlags(r1, getValues().get(r2).getFlags());
        return r1;
    }

    public abstract Clock getClock();

    public long getScheduleDelay(Priority r3, long r4, int r6) {
        long r42 = r4 - getClock().getTime();
        ConfigValue r32 = getValues().get(r3);
        return Math.min(Math.max(adjustedExponentialBackoff(r6, r32.getDelta()), r42), r32.getMaxAllowedDelay());
    }

    public abstract Map<Priority, ConfigValue> getValues();
}
