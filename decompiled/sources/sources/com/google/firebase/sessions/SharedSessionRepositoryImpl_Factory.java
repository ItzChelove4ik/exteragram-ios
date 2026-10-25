package com.google.firebase.sessions;

import androidx.datastore.core.DataStore;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;
import com.google.firebase.sessions.settings.SessionsSettings;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public final class SharedSessionRepositoryImpl_Factory implements Factory<SharedSessionRepositoryImpl> {
    private final Provider<CoroutineContext> backgroundDispatcherProvider;
    private final Provider<ProcessDataManager> processDataManagerProvider;
    private final Provider<DataStore<SessionData>> sessionDataStoreProvider;
    private final Provider<SessionFirelogPublisher> sessionFirelogPublisherProvider;
    private final Provider<SessionGenerator> sessionGeneratorProvider;
    private final Provider<SessionsSettings> sessionsSettingsProvider;
    private final Provider<TimeProvider> timeProvider;

    private SharedSessionRepositoryImpl_Factory(Provider<SessionsSettings> r1, Provider<SessionGenerator> r2, Provider<SessionFirelogPublisher> r3, Provider<TimeProvider> r4, Provider<DataStore<SessionData>> r5, Provider<ProcessDataManager> r6, Provider<CoroutineContext> r7) {
        this.sessionsSettingsProvider = r1;
        this.sessionGeneratorProvider = r2;
        this.sessionFirelogPublisherProvider = r3;
        this.timeProvider = r4;
        this.sessionDataStoreProvider = r5;
        this.processDataManagerProvider = r6;
        this.backgroundDispatcherProvider = r7;
    }

    public static SharedSessionRepositoryImpl_Factory create(Provider<SessionsSettings> r8, Provider<SessionGenerator> r9, Provider<SessionFirelogPublisher> r10, Provider<TimeProvider> r11, Provider<DataStore<SessionData>> r12, Provider<ProcessDataManager> r13, Provider<CoroutineContext> r14) {
        return new SharedSessionRepositoryImpl_Factory(r8, r9, r10, r11, r12, r13, r14);
    }

    public static SharedSessionRepositoryImpl newInstance(SessionsSettings r8, SessionGenerator r9, SessionFirelogPublisher r10, TimeProvider r11, DataStore<SessionData> r12, ProcessDataManager r13, CoroutineContext r14) {
        return new SharedSessionRepositoryImpl(r8, r9, r10, r11, r12, r13, r14);
    }

    @Override // javax.inject.Provider
    public SharedSessionRepositoryImpl get() {
        return newInstance(this.sessionsSettingsProvider.get(), this.sessionGeneratorProvider.get(), this.sessionFirelogPublisherProvider.get(), this.timeProvider.get(), this.sessionDataStoreProvider.get(), this.processDataManagerProvider.get(), this.backgroundDispatcherProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
