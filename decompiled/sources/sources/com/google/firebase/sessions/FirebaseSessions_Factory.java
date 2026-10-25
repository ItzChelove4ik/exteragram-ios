package com.google.firebase.sessions;

import com.google.firebase.FirebaseApp;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;
import com.google.firebase.sessions.settings.SessionsSettings;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public final class FirebaseSessions_Factory implements Factory<FirebaseSessions> {
    private final Provider<CoroutineContext> backgroundDispatcherProvider;
    private final Provider<FirebaseApp> firebaseAppProvider;
    private final Provider<SessionsActivityLifecycleCallbacks> sessionsActivityLifecycleCallbacksProvider;
    private final Provider<SessionsSettings> settingsProvider;

    private FirebaseSessions_Factory(Provider<FirebaseApp> r1, Provider<SessionsSettings> r2, Provider<CoroutineContext> r3, Provider<SessionsActivityLifecycleCallbacks> r4) {
        this.firebaseAppProvider = r1;
        this.settingsProvider = r2;
        this.backgroundDispatcherProvider = r3;
        this.sessionsActivityLifecycleCallbacksProvider = r4;
    }

    public static FirebaseSessions_Factory create(Provider<FirebaseApp> r1, Provider<SessionsSettings> r2, Provider<CoroutineContext> r3, Provider<SessionsActivityLifecycleCallbacks> r4) {
        return new FirebaseSessions_Factory(r1, r2, r3, r4);
    }

    public static FirebaseSessions newInstance(FirebaseApp r1, SessionsSettings r2, CoroutineContext r3, SessionsActivityLifecycleCallbacks r4) {
        return new FirebaseSessions(r1, r2, r3, r4);
    }

    @Override // javax.inject.Provider
    public FirebaseSessions get() {
        return newInstance(this.firebaseAppProvider.get(), this.settingsProvider.get(), this.backgroundDispatcherProvider.get(), this.sessionsActivityLifecycleCallbacksProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
