package com.google.firebase.sessions.settings;

import androidx.datastore.core.DataStore;
import com.google.firebase.sessions.TimeProvider;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public final class SettingsCacheImpl_Factory implements Factory<SettingsCacheImpl> {
    private final Provider<CoroutineContext> backgroundDispatcherProvider;
    private final Provider<DataStore<SessionConfigs>> sessionConfigsDataStoreProvider;
    private final Provider<TimeProvider> timeProvider;

    private SettingsCacheImpl_Factory(Provider<CoroutineContext> r1, Provider<TimeProvider> r2, Provider<DataStore<SessionConfigs>> r3) {
        this.backgroundDispatcherProvider = r1;
        this.timeProvider = r2;
        this.sessionConfigsDataStoreProvider = r3;
    }

    public static SettingsCacheImpl_Factory create(Provider<CoroutineContext> r1, Provider<TimeProvider> r2, Provider<DataStore<SessionConfigs>> r3) {
        return new SettingsCacheImpl_Factory(r1, r2, r3);
    }

    public static SettingsCacheImpl newInstance(CoroutineContext r1, TimeProvider r2, DataStore<SessionConfigs> r3) {
        return new SettingsCacheImpl(r1, r2, r3);
    }

    @Override // javax.inject.Provider
    public SettingsCacheImpl get() {
        return newInstance(this.backgroundDispatcherProvider.get(), this.timeProvider.get(), this.sessionConfigsDataStoreProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
