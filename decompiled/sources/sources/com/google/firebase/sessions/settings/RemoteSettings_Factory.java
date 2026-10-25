package com.google.firebase.sessions.settings;

import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.ApplicationInfo;
import com.google.firebase.sessions.TimeProvider;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Provider;

/* loaded from: classes.dex */
public final class RemoteSettings_Factory implements Factory<RemoteSettings> {
    private final Provider<ApplicationInfo> appInfoProvider;
    private final Provider<CrashlyticsSettingsFetcher> configsFetcherProvider;
    private final Provider<FirebaseInstallationsApi> firebaseInstallationsApiProvider;
    private final Provider<SettingsCache> settingsCacheProvider;
    private final Provider<TimeProvider> timeProvider;

    private RemoteSettings_Factory(Provider<TimeProvider> r1, Provider<FirebaseInstallationsApi> r2, Provider<ApplicationInfo> r3, Provider<CrashlyticsSettingsFetcher> r4, Provider<SettingsCache> r5) {
        this.timeProvider = r1;
        this.firebaseInstallationsApiProvider = r2;
        this.appInfoProvider = r3;
        this.configsFetcherProvider = r4;
        this.settingsCacheProvider = r5;
    }

    public static RemoteSettings_Factory create(Provider<TimeProvider> r6, Provider<FirebaseInstallationsApi> r7, Provider<ApplicationInfo> r8, Provider<CrashlyticsSettingsFetcher> r9, Provider<SettingsCache> r10) {
        return new RemoteSettings_Factory(r6, r7, r8, r9, r10);
    }

    public static RemoteSettings newInstance(TimeProvider r6, FirebaseInstallationsApi r7, ApplicationInfo r8, CrashlyticsSettingsFetcher r9, SettingsCache r10) {
        return new RemoteSettings(r6, r7, r8, r9, r10);
    }

    @Override // javax.inject.Provider
    public RemoteSettings get() {
        return newInstance(this.timeProvider.get(), this.firebaseInstallationsApiProvider.get(), this.appInfoProvider.get(), this.configsFetcherProvider.get(), this.settingsCacheProvider.get());
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        return get();
    }
}
