package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.TransportRuntimeComponent;
import com.google.android.datatransport.runtime.backends.CreationContextFactory_Factory;
import com.google.android.datatransport.runtime.backends.MetadataBackendRegistry_Factory;
import com.google.android.datatransport.runtime.dagger.internal.DoubleCheck;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.InstanceFactory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler_Factory;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule_ConfigFactory;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule_WorkSchedulerFactory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_DbNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_PackageNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_SchemaVersionFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_StoreConfigFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore_Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager_Factory;
import com.google.android.datatransport.runtime.time.TimeModule_EventClockFactory;
import com.google.android.datatransport.runtime.time.TimeModule_UptimeClockFactory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* loaded from: classes.dex */
abstract class DaggerTransportRuntimeComponent {

    /* renamed from: com.google.android.datatransport.runtime.DaggerTransportRuntimeComponent$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder implements TransportRuntimeComponent.Builder {
        private Context setApplicationContext;

        private Builder() {
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public TransportRuntimeComponent build() {
            Preconditions.checkBuilderRequirement(this.setApplicationContext, Context.class);
            return new TransportRuntimeComponentImpl(this.setApplicationContext, null);
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public Builder setApplicationContext(Context r1) {
            this.setApplicationContext = (Context) Preconditions.checkNotNull(r1);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder
        public /* bridge */ /* synthetic */ TransportRuntimeComponent.Builder setApplicationContext(Context r1) {
            return setApplicationContext(r1);
        }
    }

    public static final class TransportRuntimeComponentImpl extends TransportRuntimeComponent {
        private Provider<SchedulerConfig> configProvider;
        private Provider creationContextFactoryProvider;
        private Provider<DefaultScheduler> defaultSchedulerProvider;
        private Provider<Executor> executorProvider;
        private Provider metadataBackendRegistryProvider;
        private Provider<String> packageNameProvider;
        private Provider<SQLiteEventStore> sQLiteEventStoreProvider;
        private Provider schemaManagerProvider;
        private Provider<Context> setApplicationContextProvider;
        private final TransportRuntimeComponentImpl transportRuntimeComponentImpl;
        private Provider<TransportRuntime> transportRuntimeProvider;
        private Provider<Uploader> uploaderProvider;
        private Provider<WorkInitializer> workInitializerProvider;
        private Provider<WorkScheduler> workSchedulerProvider;

        private TransportRuntimeComponentImpl(Context r1) {
            this.transportRuntimeComponentImpl = this;
            initialize(r1);
        }

        public /* synthetic */ TransportRuntimeComponentImpl(Context r1, AnonymousClass1 r2) {
            this(r1);
        }

        private void initialize(Context r10) {
            this.executorProvider = DoubleCheck.provider(ExecutionModule_ExecutorFactory.create());
            Factory r102 = InstanceFactory.create(r10);
            this.setApplicationContextProvider = r102;
            CreationContextFactory_Factory r103 = CreationContextFactory_Factory.create(r102, TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create());
            this.creationContextFactoryProvider = r103;
            this.metadataBackendRegistryProvider = DoubleCheck.provider(MetadataBackendRegistry_Factory.create(this.setApplicationContextProvider, r103));
            this.schemaManagerProvider = SchemaManager_Factory.create(this.setApplicationContextProvider, EventStoreModule_DbNameFactory.create(), EventStoreModule_SchemaVersionFactory.create());
            this.packageNameProvider = DoubleCheck.provider(EventStoreModule_PackageNameFactory.create(this.setApplicationContextProvider));
            this.sQLiteEventStoreProvider = DoubleCheck.provider(SQLiteEventStore_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), EventStoreModule_StoreConfigFactory.create(), this.schemaManagerProvider, this.packageNameProvider));
            SchedulingConfigModule_ConfigFactory r104 = SchedulingConfigModule_ConfigFactory.create(TimeModule_EventClockFactory.create());
            this.configProvider = r104;
            SchedulingModule_WorkSchedulerFactory r105 = SchedulingModule_WorkSchedulerFactory.create(this.setApplicationContextProvider, this.sQLiteEventStoreProvider, r104, TimeModule_UptimeClockFactory.create());
            this.workSchedulerProvider = r105;
            Provider<Executor> r0 = this.executorProvider;
            Provider r1 = this.metadataBackendRegistryProvider;
            Provider<SQLiteEventStore> r2 = this.sQLiteEventStoreProvider;
            this.defaultSchedulerProvider = DefaultScheduler_Factory.create(r0, r1, r105, r2, r2);
            Provider<Context> r02 = this.setApplicationContextProvider;
            Provider r12 = this.metadataBackendRegistryProvider;
            Provider<SQLiteEventStore> r22 = this.sQLiteEventStoreProvider;
            this.uploaderProvider = Uploader_Factory.create(r02, r12, r22, this.workSchedulerProvider, this.executorProvider, r22, TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), this.sQLiteEventStoreProvider);
            Provider<Executor> r106 = this.executorProvider;
            Provider<SQLiteEventStore> r03 = this.sQLiteEventStoreProvider;
            this.workInitializerProvider = WorkInitializer_Factory.create(r106, r03, this.workSchedulerProvider, r03);
            this.transportRuntimeProvider = DoubleCheck.provider(TransportRuntime_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), this.defaultSchedulerProvider, this.uploaderProvider, this.workInitializerProvider));
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent
        public EventStore getEventStore() {
            return this.sQLiteEventStoreProvider.get();
        }

        @Override // com.google.android.datatransport.runtime.TransportRuntimeComponent
        public TransportRuntime getTransportRuntime() {
            return this.transportRuntimeProvider.get();
        }
    }

    public static TransportRuntimeComponent.Builder builder() {
        return new Builder(null);
    }
}
