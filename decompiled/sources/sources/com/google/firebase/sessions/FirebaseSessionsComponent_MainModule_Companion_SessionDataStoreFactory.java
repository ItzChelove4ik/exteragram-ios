package com.google.firebase.sessions;

import android.content.Context;
import androidx.datastore.core.DataStore;
import com.google.firebase.sessions.FirebaseSessionsComponent;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Preconditions;
import com.google.firebase.sessions.dagger.internal.Provider;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public final class FirebaseSessionsComponent_MainModule_Companion_SessionDataStoreFactory implements Factory<DataStore<SessionData>> {
    private final Provider<Context> appContextProvider;
    private final Provider<CoroutineContext> blockingDispatcherProvider;
    private final Provider<SessionDataSerializer> sessionDataSerializerProvider;

    private FirebaseSessionsComponent_MainModule_Companion_SessionDataStoreFactory(Provider<Context> r1, Provider<CoroutineContext> r2, Provider<SessionDataSerializer> r3) {
        this.appContextProvider = r1;
        this.blockingDispatcherProvider = r2;
        this.sessionDataSerializerProvider = r3;
    }

    public static FirebaseSessionsComponent_MainModule_Companion_SessionDataStoreFactory create(Provider<Context> r1, Provider<CoroutineContext> r2, Provider<SessionDataSerializer> r3) {
        return new FirebaseSessionsComponent_MainModule_Companion_SessionDataStoreFactory(r1, r2, r3);
    }

    public static DataStore<SessionData> sessionDataStore(Context r1, CoroutineContext r2, SessionDataSerializer r3) {
        return (DataStore) Preconditions.checkNotNullFromProvides(FirebaseSessionsComponent.MainModule.Companion.sessionDataStore(r1, r2, r3));
    }

    @Override // javax.inject.Provider
    public DataStore<SessionData> get() {
        return sessionDataStore(this.appContextProvider.get(), this.blockingDispatcherProvider.get(), this.sessionDataSerializerProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
