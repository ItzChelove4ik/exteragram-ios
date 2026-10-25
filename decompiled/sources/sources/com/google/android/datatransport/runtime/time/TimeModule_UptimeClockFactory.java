package com.google.android.datatransport.runtime.time;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class TimeModule_UptimeClockFactory implements Factory<Clock> {

    public static final class InstanceHolder {
        private static final TimeModule_UptimeClockFactory INSTANCE = null;

        static {
            INSTANCE = new TimeModule_UptimeClockFactory();
        }

        public static /* synthetic */ TimeModule_UptimeClockFactory access$000() {
            return INSTANCE;
        }
    }

    public TimeModule_UptimeClockFactory() {
    }

    public static TimeModule_UptimeClockFactory create() {
        return InstanceHolder.access$000();
    }

    public static Clock uptimeClock() {
        return (Clock) Preconditions.checkNotNullFromProvides(TimeModule.uptimeClock());
    }

    @Override // javax.inject.Provider
    public Clock get() {
        return uptimeClock();
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
