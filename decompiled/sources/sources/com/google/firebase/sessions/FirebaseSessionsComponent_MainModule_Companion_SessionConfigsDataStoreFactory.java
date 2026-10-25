package com.google.firebase.sessions;

import android.content.Context;
import androidx.datastore.core.DataStore;
import com.google.firebase.sessions.FirebaseSessionsComponent;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Preconditions;
import com.google.firebase.sessions.dagger.internal.Provider;
import com.google.firebase.sessions.settings.SessionConfigs;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public final class FirebaseSessionsComponent_MainModule_Companion_SessionConfigsDataStoreFactory implements Factory<DataStore<SessionConfigs>> {
    private final Provider<Context> appContextProvider;
    private final Provider<CoroutineContext> blockingDispatcherProvider;

    private FirebaseSessionsComponent_MainModule_Companion_SessionConfigsDataStoreFactory(Provider<Context> r1, Provider<CoroutineContext> r2) {
        this.appContextProvider = r1;
        this.blockingDispatcherProvider = r2;
    }

    public static FirebaseSessionsComponent_MainModule_Companion_SessionConfigsDataStoreFactory create(Provider<Context> r1, Provider<CoroutineContext> r2) {
        return new FirebaseSessionsComponent_MainModule_Companion_SessionConfigsDataStoreFactory(r1, r2);
    }

    public static DataStore<SessionConfigs> sessionConfigsDataStore(Context r1, CoroutineContext r2) {
        return (DataStore) Preconditions.checkNotNullFromProvides(FirebaseSessionsComponent.MainModule.Companion.sessionConfigsDataStore(r1, r2));
    }

    @Override // javax.inject.Provider
    public DataStore<SessionConfigs> get() {
        return sessionConfigsDataStore(this.appContextProvider.get(), this.blockingDispatcherProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
