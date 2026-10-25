package com.google.firebase.sessions;

import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;
import com.google.firebase.sessions.settings.SessionsSettings;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public final class SessionFirelogPublisherImpl_Factory implements Factory<SessionFirelogPublisherImpl> {
    private final Provider<CoroutineContext> backgroundDispatcherProvider;
    private final Provider<EventGDTLoggerInterface> eventGDTLoggerProvider;
    private final Provider<FirebaseApp> firebaseAppProvider;
    private final Provider<FirebaseInstallationsApi> firebaseInstallationsProvider;
    private final Provider<SessionsSettings> sessionSettingsProvider;

    private SessionFirelogPublisherImpl_Factory(Provider<FirebaseApp> r1, Provider<FirebaseInstallationsApi> r2, Provider<SessionsSettings> r3, Provider<EventGDTLoggerInterface> r4, Provider<CoroutineContext> r5) {
        this.firebaseAppProvider = r1;
        this.firebaseInstallationsProvider = r2;
        this.sessionSettingsProvider = r3;
        this.eventGDTLoggerProvider = r4;
        this.backgroundDispatcherProvider = r5;
    }

    public static SessionFirelogPublisherImpl_Factory create(Provider<FirebaseApp> r6, Provider<FirebaseInstallationsApi> r7, Provider<SessionsSettings> r8, Provider<EventGDTLoggerInterface> r9, Provider<CoroutineContext> r10) {
        return new SessionFirelogPublisherImpl_Factory(r6, r7, r8, r9, r10);
    }

    public static SessionFirelogPublisherImpl newInstance(FirebaseApp r6, FirebaseInstallationsApi r7, SessionsSettings r8, EventGDTLoggerInterface r9, CoroutineContext r10) {
        return new SessionFirelogPublisherImpl(r6, r7, r8, r9, r10);
    }

    @Override // javax.inject.Provider
    public SessionFirelogPublisherImpl get() {
        return newInstance(this.firebaseAppProvider.get(), this.firebaseInstallationsProvider.get(), this.sessionSettingsProvider.get(), this.eventGDTLoggerProvider.get(), this.backgroundDispatcherProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
