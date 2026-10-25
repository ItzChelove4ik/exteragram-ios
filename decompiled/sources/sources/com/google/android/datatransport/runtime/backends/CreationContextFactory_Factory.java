package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class CreationContextFactory_Factory implements Factory<CreationContextFactory> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<Clock> monotonicClockProvider;
    private final Provider<Clock> wallClockProvider;

    public CreationContextFactory_Factory(Provider<Context> r1, Provider<Clock> r2, Provider<Clock> r3) {
        this.applicationContextProvider = r1;
        this.wallClockProvider = r2;
        this.monotonicClockProvider = r3;
    }

    public static CreationContextFactory_Factory create(Provider<Context> r1, Provider<Clock> r2, Provider<Clock> r3) {
        return new CreationContextFactory_Factory(r1, r2, r3);
    }

    public static CreationContextFactory newInstance(Context r1, Clock r2, Clock r3) {
        return new CreationContextFactory(r1, r2, r3);
    }

    @Override // javax.inject.Provider
    public CreationContextFactory get() {
        return newInstance(this.applicationContextProvider.get(), this.wallClockProvider.get(), this.monotonicClockProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
