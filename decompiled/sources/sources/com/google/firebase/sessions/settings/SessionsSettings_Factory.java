package com.google.firebase.sessions.settings;

import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;

/* loaded from: classes.dex */
public final class SessionsSettings_Factory implements Factory<SessionsSettings> {
    private final Provider<SettingsProvider> localOverrideSettingsProvider;
    private final Provider<SettingsProvider> remoteSettingsProvider;

    private SessionsSettings_Factory(Provider<SettingsProvider> r1, Provider<SettingsProvider> r2) {
        this.localOverrideSettingsProvider = r1;
        this.remoteSettingsProvider = r2;
    }

    public static SessionsSettings_Factory create(Provider<SettingsProvider> r1, Provider<SettingsProvider> r2) {
        return new SessionsSettings_Factory(r1, r2);
    }

    public static SessionsSettings newInstance(SettingsProvider r1, SettingsProvider r2) {
        return new SessionsSettings(r1, r2);
    }

    @Override // javax.inject.Provider
    public SessionsSettings get() {
        return newInstance(this.localOverrideSettingsProvider.get(), this.remoteSettingsProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
