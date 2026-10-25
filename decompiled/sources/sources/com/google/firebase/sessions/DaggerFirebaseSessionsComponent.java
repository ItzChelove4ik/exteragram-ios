package com.google.firebase.sessions;

import android.content.Context;
import androidx.datastore.core.DataStore;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.FirebaseSessionsComponent;
import com.google.firebase.sessions.dagger.internal.DoubleCheck;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.InstanceFactory;
import com.google.firebase.sessions.dagger.internal.Preconditions;
import com.google.firebase.sessions.settings.LocalOverrideSettings;
import com.google.firebase.sessions.settings.LocalOverrideSettings_Factory;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.google.firebase.sessions.settings.RemoteSettingsFetcher;
import com.google.firebase.sessions.settings.RemoteSettingsFetcher_Factory;
import com.google.firebase.sessions.settings.RemoteSettings_Factory;
import com.google.firebase.sessions.settings.SessionConfigs;
import com.google.firebase.sessions.settings.SessionsSettings;
import com.google.firebase.sessions.settings.SessionsSettings_Factory;
import com.google.firebase.sessions.settings.SettingsCacheImpl;
import com.google.firebase.sessions.settings.SettingsCacheImpl_Factory;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public abstract class DaggerFirebaseSessionsComponent {

    /* renamed from: com.google.firebase.sessions.DaggerFirebaseSessionsComponent$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder implements FirebaseSessionsComponent.Builder {
        private Context appContext;
        private CoroutineContext backgroundDispatcher;
        private CoroutineContext blockingDispatcher;
        private FirebaseApp firebaseApp;
        private FirebaseInstallationsApi firebaseInstallationsApi;
        private Provider<TransportFactory> transportFactoryProvider;

        private Builder() {
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public Builder appContext(Context r1) {
            this.appContext = (Context) Preconditions.checkNotNull(r1);
            return this;
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public /* bridge */ /* synthetic */ FirebaseSessionsComponent.Builder appContext(Context r1) {
            return appContext(r1);
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public Builder backgroundDispatcher(CoroutineContext r1) {
            this.backgroundDispatcher = (CoroutineContext) Preconditions.checkNotNull(r1);
            return this;
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public /* bridge */ /* synthetic */ FirebaseSessionsComponent.Builder backgroundDispatcher(CoroutineContext r1) {
            return backgroundDispatcher(r1);
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public Builder blockingDispatcher(CoroutineContext r1) {
            this.blockingDispatcher = (CoroutineContext) Preconditions.checkNotNull(r1);
            return this;
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public /* bridge */ /* synthetic */ FirebaseSessionsComponent.Builder blockingDispatcher(CoroutineContext r1) {
            return blockingDispatcher(r1);
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public FirebaseSessionsComponent build() {
            Preconditions.checkBuilderRequirement(this.appContext, Context.class);
            Preconditions.checkBuilderRequirement(this.backgroundDispatcher, CoroutineContext.class);
            Preconditions.checkBuilderRequirement(this.blockingDispatcher, CoroutineContext.class);
            Preconditions.checkBuilderRequirement(this.firebaseApp, FirebaseApp.class);
            Preconditions.checkBuilderRequirement(this.firebaseInstallationsApi, FirebaseInstallationsApi.class);
            Preconditions.checkBuilderRequirement(this.transportFactoryProvider, Provider.class);
            return new FirebaseSessionsComponentImpl(this.appContext, this.backgroundDispatcher, this.blockingDispatcher, this.firebaseApp, this.firebaseInstallationsApi, this.transportFactoryProvider);
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public Builder firebaseApp(FirebaseApp r1) {
            this.firebaseApp = (FirebaseApp) Preconditions.checkNotNull(r1);
            return this;
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public /* bridge */ /* synthetic */ FirebaseSessionsComponent.Builder firebaseApp(FirebaseApp r1) {
            return firebaseApp(r1);
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public Builder firebaseInstallationsApi(FirebaseInstallationsApi r1) {
            this.firebaseInstallationsApi = (FirebaseInstallationsApi) Preconditions.checkNotNull(r1);
            return this;
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public /* bridge */ /* synthetic */ FirebaseSessionsComponent.Builder firebaseInstallationsApi(FirebaseInstallationsApi r1) {
            return firebaseInstallationsApi(r1);
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public Builder transportFactoryProvider(Provider<TransportFactory> r1) {
            this.transportFactoryProvider = (Provider) Preconditions.checkNotNull(r1);
            return this;
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent.Builder
        public /* bridge */ /* synthetic */ FirebaseSessionsComponent.Builder transportFactoryProvider(Provider r1) {
            return transportFactoryProvider(r1);
        }
    }

    public static final class FirebaseSessionsComponentImpl implements FirebaseSessionsComponent {
        com.google.firebase.sessions.dagger.internal.Provider<Context> appContextProvider;
        com.google.firebase.sessions.dagger.internal.Provider<ApplicationInfo> applicationInfoProvider;
        com.google.firebase.sessions.dagger.internal.Provider<CoroutineContext> backgroundDispatcherProvider;
        com.google.firebase.sessions.dagger.internal.Provider<CoroutineContext> blockingDispatcherProvider;
        com.google.firebase.sessions.dagger.internal.Provider<EventGDTLogger> eventGDTLoggerProvider;
        com.google.firebase.sessions.dagger.internal.Provider<FirebaseApp> firebaseAppProvider;
        com.google.firebase.sessions.dagger.internal.Provider<FirebaseInstallationsApi> firebaseInstallationsApiProvider;
        private final FirebaseSessionsComponentImpl firebaseSessionsComponentImpl;
        com.google.firebase.sessions.dagger.internal.Provider<FirebaseSessions> firebaseSessionsProvider;
        com.google.firebase.sessions.dagger.internal.Provider<LocalOverrideSettings> localOverrideSettingsProvider;
        com.google.firebase.sessions.dagger.internal.Provider<ProcessDataManagerImpl> processDataManagerImplProvider;
        com.google.firebase.sessions.dagger.internal.Provider<RemoteSettingsFetcher> remoteSettingsFetcherProvider;
        com.google.firebase.sessions.dagger.internal.Provider<RemoteSettings> remoteSettingsProvider;
        com.google.firebase.sessions.dagger.internal.Provider<DataStore<SessionConfigs>> sessionConfigsDataStoreProvider;
        com.google.firebase.sessions.dagger.internal.Provider<SessionDataSerializer> sessionDataSerializerProvider;
        com.google.firebase.sessions.dagger.internal.Provider<DataStore<SessionData>> sessionDataStoreProvider;
        com.google.firebase.sessions.dagger.internal.Provider<SessionFirelogPublisherImpl> sessionFirelogPublisherImplProvider;
        com.google.firebase.sessions.dagger.internal.Provider<SessionGenerator> sessionGeneratorProvider;
        com.google.firebase.sessions.dagger.internal.Provider<SessionsActivityLifecycleCallbacks> sessionsActivityLifecycleCallbacksProvider;
        com.google.firebase.sessions.dagger.internal.Provider<SessionsSettings> sessionsSettingsProvider;
        com.google.firebase.sessions.dagger.internal.Provider<SettingsCacheImpl> settingsCacheImplProvider;
        com.google.firebase.sessions.dagger.internal.Provider<SharedSessionRepositoryImpl> sharedSessionRepositoryImplProvider;
        com.google.firebase.sessions.dagger.internal.Provider<TimeProvider> timeProvider;
        com.google.firebase.sessions.dagger.internal.Provider<Provider<TransportFactory>> transportFactoryProvider;
        com.google.firebase.sessions.dagger.internal.Provider<UuidGenerator> uuidGeneratorProvider;

        public FirebaseSessionsComponentImpl(Context r1, CoroutineContext r2, CoroutineContext r3, FirebaseApp r4, FirebaseInstallationsApi r5, Provider<TransportFactory> r6) {
            this.firebaseSessionsComponentImpl = this;
            initialize(r1, r2, r3, r4, r5, r6);
        }

        private void initialize(Context r8, CoroutineContext r9, CoroutineContext r10, FirebaseApp r11, FirebaseInstallationsApi r12, Provider<TransportFactory> r13) {
            this.firebaseAppProvider = InstanceFactory.create(r11);
            Factory r82 = InstanceFactory.create(r8);
            this.appContextProvider = r82;
            this.localOverrideSettingsProvider = DoubleCheck.provider(LocalOverrideSettings_Factory.create(r82));
            this.timeProvider = DoubleCheck.provider(FirebaseSessionsComponent_MainModule_Companion_TimeProviderFactory.create());
            this.firebaseInstallationsApiProvider = InstanceFactory.create(r12);
            this.applicationInfoProvider = DoubleCheck.provider(FirebaseSessionsComponent_MainModule_Companion_ApplicationInfoFactory.create(this.firebaseAppProvider));
            Factory r83 = InstanceFactory.create(r10);
            this.blockingDispatcherProvider = r83;
            this.remoteSettingsFetcherProvider = DoubleCheck.provider(RemoteSettingsFetcher_Factory.create(this.applicationInfoProvider, r83));
            this.backgroundDispatcherProvider = InstanceFactory.create(r9);
            com.google.firebase.sessions.dagger.internal.Provider<DataStore<SessionConfigs>> r84 = DoubleCheck.provider(FirebaseSessionsComponent_MainModule_Companion_SessionConfigsDataStoreFactory.create(this.appContextProvider, this.blockingDispatcherProvider));
            this.sessionConfigsDataStoreProvider = r84;
            com.google.firebase.sessions.dagger.internal.Provider<SettingsCacheImpl> r85 = DoubleCheck.provider(SettingsCacheImpl_Factory.create(this.backgroundDispatcherProvider, this.timeProvider, r84));
            this.settingsCacheImplProvider = r85;
            com.google.firebase.sessions.dagger.internal.Provider<RemoteSettings> r86 = DoubleCheck.provider(RemoteSettings_Factory.create(this.timeProvider, this.firebaseInstallationsApiProvider, this.applicationInfoProvider, this.remoteSettingsFetcherProvider, r85));
            this.remoteSettingsProvider = r86;
            this.sessionsSettingsProvider = DoubleCheck.provider(SessionsSettings_Factory.create(this.localOverrideSettingsProvider, r86));
            com.google.firebase.sessions.dagger.internal.Provider<UuidGenerator> r87 = DoubleCheck.provider(FirebaseSessionsComponent_MainModule_Companion_UuidGeneratorFactory.create());
            this.uuidGeneratorProvider = r87;
            this.sessionGeneratorProvider = DoubleCheck.provider(SessionGenerator_Factory.create(this.timeProvider, r87));
            Factory r88 = InstanceFactory.create(r13);
            this.transportFactoryProvider = r88;
            com.google.firebase.sessions.dagger.internal.Provider<EventGDTLogger> r89 = DoubleCheck.provider(EventGDTLogger_Factory.create(r88));
            this.eventGDTLoggerProvider = r89;
            this.sessionFirelogPublisherImplProvider = DoubleCheck.provider(SessionFirelogPublisherImpl_Factory.create(this.firebaseAppProvider, this.firebaseInstallationsApiProvider, this.sessionsSettingsProvider, r89, this.backgroundDispatcherProvider));
            com.google.firebase.sessions.dagger.internal.Provider<SessionDataSerializer> r810 = DoubleCheck.provider(SessionDataSerializer_Factory.create(this.sessionGeneratorProvider));
            this.sessionDataSerializerProvider = r810;
            this.sessionDataStoreProvider = DoubleCheck.provider(FirebaseSessionsComponent_MainModule_Companion_SessionDataStoreFactory.create(this.appContextProvider, this.blockingDispatcherProvider, r810));
            com.google.firebase.sessions.dagger.internal.Provider<ProcessDataManagerImpl> r5 = DoubleCheck.provider(ProcessDataManagerImpl_Factory.create(this.appContextProvider, this.uuidGeneratorProvider));
            this.processDataManagerImplProvider = r5;
            com.google.firebase.sessions.dagger.internal.Provider<SharedSessionRepositoryImpl> r811 = DoubleCheck.provider(SharedSessionRepositoryImpl_Factory.create(this.sessionsSettingsProvider, this.sessionGeneratorProvider, this.sessionFirelogPublisherImplProvider, this.timeProvider, this.sessionDataStoreProvider, r5, this.backgroundDispatcherProvider));
            this.sharedSessionRepositoryImplProvider = r811;
            com.google.firebase.sessions.dagger.internal.Provider<SessionsActivityLifecycleCallbacks> r812 = DoubleCheck.provider(SessionsActivityLifecycleCallbacks_Factory.create(r811));
            this.sessionsActivityLifecycleCallbacksProvider = r812;
            this.firebaseSessionsProvider = DoubleCheck.provider(FirebaseSessions_Factory.create(this.firebaseAppProvider, this.sessionsSettingsProvider, this.backgroundDispatcherProvider, r812));
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent
        public FirebaseSessions getFirebaseSessions() {
            return this.firebaseSessionsProvider.get();
        }

        @Override // com.google.firebase.sessions.FirebaseSessionsComponent
        public SharedSessionRepository getSharedSessionRepository() {
            return this.sharedSessionRepositoryImplProvider.get();
        }
    }

    public static FirebaseSessionsComponent.Builder builder() {
        return new Builder(null);
    }
}
