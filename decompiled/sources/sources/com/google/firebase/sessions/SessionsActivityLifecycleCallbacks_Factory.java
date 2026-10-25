package com.google.firebase.sessions;

import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;

/* loaded from: classes.dex */
public final class SessionsActivityLifecycleCallbacks_Factory implements Factory<SessionsActivityLifecycleCallbacks> {
    private final Provider<SharedSessionRepository> sharedSessionRepositoryProvider;

    private SessionsActivityLifecycleCallbacks_Factory(Provider<SharedSessionRepository> r1) {
        this.sharedSessionRepositoryProvider = r1;
    }

    public static SessionsActivityLifecycleCallbacks_Factory create(Provider<SharedSessionRepository> r1) {
        return new SessionsActivityLifecycleCallbacks_Factory(r1);
    }

    public static SessionsActivityLifecycleCallbacks newInstance(SharedSessionRepository r1) {
        return new SessionsActivityLifecycleCallbacks(r1);
    }

    @Override // javax.inject.Provider
    public SessionsActivityLifecycleCallbacks get() {
        return newInstance(this.sharedSessionRepositoryProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
