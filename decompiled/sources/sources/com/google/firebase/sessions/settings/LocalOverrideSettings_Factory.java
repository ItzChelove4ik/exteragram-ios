package com.google.firebase.sessions.settings;

import android.content.Context;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;

/* loaded from: classes.dex */
public final class LocalOverrideSettings_Factory implements Factory<LocalOverrideSettings> {
    private final Provider<Context> appContextProvider;

    private LocalOverrideSettings_Factory(Provider<Context> r1) {
        this.appContextProvider = r1;
    }

    public static LocalOverrideSettings_Factory create(Provider<Context> r1) {
        return new LocalOverrideSettings_Factory(r1);
    }

    public static LocalOverrideSettings newInstance(Context r1) {
        return new LocalOverrideSettings(r1);
    }

    @Override // javax.inject.Provider
    public LocalOverrideSettings get() {
        return newInstance(this.appContextProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
