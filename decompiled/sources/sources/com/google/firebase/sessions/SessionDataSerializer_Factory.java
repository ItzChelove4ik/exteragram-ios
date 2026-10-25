package com.google.firebase.sessions;

import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;

/* loaded from: classes.dex */
public final class SessionDataSerializer_Factory implements Factory<SessionDataSerializer> {
    private final Provider<SessionGenerator> sessionGeneratorProvider;

    private SessionDataSerializer_Factory(Provider<SessionGenerator> r1) {
        this.sessionGeneratorProvider = r1;
    }

    public static SessionDataSerializer_Factory create(Provider<SessionGenerator> r1) {
        return new SessionDataSerializer_Factory(r1);
    }

    public static SessionDataSerializer newInstance(SessionGenerator r1) {
        return new SessionDataSerializer(r1);
    }

    @Override // javax.inject.Provider
    public SessionDataSerializer get() {
        return newInstance(this.sessionGeneratorProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
