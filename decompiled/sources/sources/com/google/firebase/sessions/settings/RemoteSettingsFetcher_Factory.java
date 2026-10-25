package com.google.firebase.sessions.settings;

import com.google.firebase.sessions.ApplicationInfo;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public final class RemoteSettingsFetcher_Factory implements Factory<RemoteSettingsFetcher> {
    private final Provider<ApplicationInfo> appInfoProvider;
    private final Provider<CoroutineContext> blockingDispatcherProvider;

    private RemoteSettingsFetcher_Factory(Provider<ApplicationInfo> r1, Provider<CoroutineContext> r2) {
        this.appInfoProvider = r1;
        this.blockingDispatcherProvider = r2;
    }

    public static RemoteSettingsFetcher_Factory create(Provider<ApplicationInfo> r1, Provider<CoroutineContext> r2) {
        return new RemoteSettingsFetcher_Factory(r1, r2);
    }

    public static RemoteSettingsFetcher newInstance(ApplicationInfo r1, CoroutineContext r2) {
        return new RemoteSettingsFetcher(r1, r2);
    }

    @Override // javax.inject.Provider
    public RemoteSettingsFetcher get() {
        return newInstance(this.appInfoProvider.get(), this.blockingDispatcherProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
