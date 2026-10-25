package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class EventStoreModule_PackageNameFactory implements Factory<String> {
    private final Provider<Context> contextProvider;

    public EventStoreModule_PackageNameFactory(Provider<Context> r1) {
        this.contextProvider = r1;
    }

    public static EventStoreModule_PackageNameFactory create(Provider<Context> r1) {
        return new EventStoreModule_PackageNameFactory(r1);
    }

    public static String packageName(Context r0) {
        return (String) Preconditions.checkNotNullFromProvides(EventStoreModule.packageName(r0));
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }

    @Override // javax.inject.Provider
    public String get() {
        return packageName(this.contextProvider.get());
    }
}
