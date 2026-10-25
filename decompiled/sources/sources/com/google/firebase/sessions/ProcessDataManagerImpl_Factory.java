package com.google.firebase.sessions;

import android.content.Context;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;

/* loaded from: classes.dex */
public final class ProcessDataManagerImpl_Factory implements Factory<ProcessDataManagerImpl> {
    private final Provider<Context> appContextProvider;
    private final Provider<UuidGenerator> uuidGeneratorProvider;

    private ProcessDataManagerImpl_Factory(Provider<Context> r1, Provider<UuidGenerator> r2) {
        this.appContextProvider = r1;
        this.uuidGeneratorProvider = r2;
    }

    public static ProcessDataManagerImpl_Factory create(Provider<Context> r1, Provider<UuidGenerator> r2) {
        return new ProcessDataManagerImpl_Factory(r1, r2);
    }

    public static ProcessDataManagerImpl newInstance(Context r1, UuidGenerator r2) {
        return new ProcessDataManagerImpl(r1, r2);
    }

    @Override // javax.inject.Provider
    public ProcessDataManagerImpl get() {
        return newInstance(this.appContextProvider.get(), this.uuidGeneratorProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
