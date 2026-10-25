package com.google.android.datatransport.runtime.time;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class TimeModule_EventClockFactory implements Factory<Clock> {

    public static final class InstanceHolder {
        private static final TimeModule_EventClockFactory INSTANCE = null;

        static {
            INSTANCE = new TimeModule_EventClockFactory();
        }

        public static /* synthetic */ TimeModule_EventClockFactory access$000() {
            return INSTANCE;
        }
    }

    public TimeModule_EventClockFactory() {
    }

    public static TimeModule_EventClockFactory create() {
        return InstanceHolder.access$000();
    }

    public static Clock eventClock() {
        return (Clock) Preconditions.checkNotNullFromProvides(TimeModule.eventClock());
    }

    @Override // javax.inject.Provider
    public Clock get() {
        return eventClock();
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
