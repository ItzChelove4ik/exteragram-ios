package com.google.firebase.sessions;

import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;

/* loaded from: classes.dex */
public final class SessionGenerator_Factory implements Factory<SessionGenerator> {
    private final Provider<TimeProvider> timeProvider;
    private final Provider<UuidGenerator> uuidGeneratorProvider;

    private SessionGenerator_Factory(Provider<TimeProvider> r1, Provider<UuidGenerator> r2) {
        this.timeProvider = r1;
        this.uuidGeneratorProvider = r2;
    }

    public static SessionGenerator_Factory create(Provider<TimeProvider> r1, Provider<UuidGenerator> r2) {
        return new SessionGenerator_Factory(r1, r2);
    }

    public static SessionGenerator newInstance(TimeProvider r1, UuidGenerator r2) {
        return new SessionGenerator(r1, r2);
    }

    @Override // javax.inject.Provider
    public SessionGenerator get() {
        return newInstance(this.timeProvider.get(), this.uuidGeneratorProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
