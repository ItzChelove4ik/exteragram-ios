package androidx.camera.camera2.pipe.config;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraBackends;
import androidx.camera.camera2.pipe.CameraContext;
import androidx.camera.camera2.pipe.CameraController;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.CameraSurfaceManager;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.StrictMode;
import androidx.camera.camera2.pipe.compat.AndroidExtensionSessionFactory;
import androidx.camera.camera2.pipe.compat.AndroidMHighSpeedSessionFactory;
import androidx.camera.camera2.pipe.compat.AndroidMSessionFactory;
import androidx.camera.camera2.pipe.compat.AndroidNSessionFactory;
import androidx.camera.camera2.pipe.compat.AndroidPSessionFactory;
import androidx.camera.camera2.pipe.compat.AudioRestrictionControllerImpl;
import androidx.camera.camera2.pipe.compat.Camera2Backend;
import androidx.camera.camera2.pipe.compat.Camera2CameraAvailabilityMonitor;
import androidx.camera.camera2.pipe.compat.Camera2CameraController;
import androidx.camera.camera2.pipe.compat.Camera2CameraOpener;
import androidx.camera.camera2.pipe.compat.Camera2CaptureSessionsModule_ProvideSessionFactoryFactory;
import androidx.camera.camera2.pipe.compat.Camera2DeviceCache;
import androidx.camera.camera2.pipe.compat.Camera2DeviceCloserImpl;
import androidx.camera.camera2.pipe.compat.Camera2ErrorProcessor;
import androidx.camera.camera2.pipe.compat.Camera2MetadataCache;
import androidx.camera.camera2.pipe.compat.Camera2Quirks;
import androidx.camera.camera2.pipe.compat.CameraStateOpener;
import androidx.camera.camera2.pipe.compat.CaptureSessionFactory;
import androidx.camera.camera2.pipe.compat.ConcurrentSessionSequencers;
import androidx.camera.camera2.pipe.compat.DevicePolicyManagerWrapper;
import androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager;
import androidx.camera.camera2.pipe.compat.RetryingCameraStateOpenerImpl;
import androidx.camera.camera2.pipe.compat.StandardCamera2CaptureSequenceProcessorFactory;
import androidx.camera.camera2.pipe.config.Camera2ControllerComponent;
import androidx.camera.camera2.pipe.config.CameraGraphComponent;
import androidx.camera.camera2.pipe.core.Permissions;
import androidx.camera.camera2.pipe.core.SystemClockOffsets;
import androidx.camera.camera2.pipe.core.SystemTimeSource;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.graph.CameraGraphImpl;
import androidx.camera.camera2.pipe.graph.Controller3A;
import androidx.camera.camera2.pipe.graph.GraphProcessorImpl;
import androidx.camera.camera2.pipe.graph.GraphState3A;
import androidx.camera.camera2.pipe.graph.Listener3A;
import androidx.camera.camera2.pipe.graph.StreamGraphImpl;
import androidx.camera.camera2.pipe.graph.SurfaceGraph;
import androidx.camera.camera2.pipe.internal.CameraDevicesImpl;
import androidx.camera.camera2.pipe.internal.CameraGraphParametersImpl;
import androidx.camera.camera2.pipe.internal.CameraGraphRequestListenersImpl;
import androidx.camera.camera2.pipe.internal.CameraPipeLifetime;
import androidx.camera.camera2.pipe.internal.CameraStatusMonitor;
import androidx.camera.camera2.pipe.internal.FrameCaptureQueue;
import androidx.camera.camera2.pipe.internal.FrameDistributor;
import androidx.camera.camera2.pipe.internal.GraphSessionLock;
import androidx.camera.camera2.pipe.media.ImageReaderImageSources;
import androidx.camera.camera2.pipe.media.ImageSources;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompatFactory;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.List;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* loaded from: classes3.dex */
public abstract class DaggerCameraPipeComponent {

    /* renamed from: androidx.camera.camera2.pipe.config.DaggerCameraPipeComponent$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private CameraPipeConfigModule cameraPipeConfigModule;
        private ThreadConfigModule threadConfigModule;

        private Builder() {
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        public CameraPipeComponent build() {
            Preconditions.checkBuilderRequirement(this.cameraPipeConfigModule, CameraPipeConfigModule.class);
            Preconditions.checkBuilderRequirement(this.threadConfigModule, ThreadConfigModule.class);
            return new CameraPipeComponentImpl(this.cameraPipeConfigModule, this.threadConfigModule);
        }

        public Builder cameraPipeConfigModule(CameraPipeConfigModule r1) {
            this.cameraPipeConfigModule = (CameraPipeConfigModule) Preconditions.checkNotNull(r1);
            return this;
        }

        public Builder threadConfigModule(ThreadConfigModule r1) {
            this.threadConfigModule = (ThreadConfigModule) Preconditions.checkNotNull(r1);
            return this;
        }
    }

    public static final class Camera2ControllerComponentBuilder implements Camera2ControllerComponent.Builder {
        private Camera2ControllerConfig camera2ControllerConfig;
        private final CameraPipeComponentImpl cameraPipeComponentImpl;

        private Camera2ControllerComponentBuilder(CameraPipeComponentImpl r1) {
            this.cameraPipeComponentImpl = r1;
        }

        public /* synthetic */ Camera2ControllerComponentBuilder(CameraPipeComponentImpl r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.camera.camera2.pipe.config.Camera2ControllerComponent.Builder
        public Camera2ControllerComponent build() {
            Preconditions.checkBuilderRequirement(this.camera2ControllerConfig, Camera2ControllerConfig.class);
            return new Camera2ControllerComponentImpl(this.cameraPipeComponentImpl, this.camera2ControllerConfig);
        }

        @Override // androidx.camera.camera2.pipe.config.Camera2ControllerComponent.Builder
        public /* bridge */ /* synthetic */ Camera2ControllerComponent.Builder camera2ControllerConfig(Camera2ControllerConfig r1) {
            return camera2ControllerConfig(r1);
        }

        @Override // androidx.camera.camera2.pipe.config.Camera2ControllerComponent.Builder
        public Camera2ControllerComponentBuilder camera2ControllerConfig(Camera2ControllerConfig r1) {
            this.camera2ControllerConfig = (Camera2ControllerConfig) Preconditions.checkNotNull(r1);
            return this;
        }
    }

    public static final class Camera2ControllerComponentImpl implements Camera2ControllerComponent {
        Provider<AndroidExtensionSessionFactory> androidExtensionSessionFactoryProvider;
        Provider<AndroidMHighSpeedSessionFactory> androidMHighSpeedSessionFactoryProvider;
        Provider<AndroidMSessionFactory> androidMSessionFactoryProvider;
        Provider<AndroidNSessionFactory> androidNSessionFactoryProvider;
        Provider<AndroidPSessionFactory> androidPSessionFactoryProvider;
        Provider<Camera2CameraController> camera2CameraControllerProvider;
        private final Camera2ControllerComponentImpl camera2ControllerComponentImpl;
        private final Camera2ControllerConfig camera2ControllerConfig;
        private final CameraPipeComponentImpl cameraPipeComponentImpl;
        Provider<CameraStatusMonitor> provideCameraStatusMonitorProvider;
        Provider<CoroutineScope> provideCoroutineScopeProvider;
        Provider<CaptureSessionFactory> provideSessionFactoryProvider;

        public static final class SwitchingProvider<T> implements Provider<T> {
            private final Camera2ControllerComponentImpl camera2ControllerComponentImpl;
            private final CameraPipeComponentImpl cameraPipeComponentImpl;
            private final int id;

            public SwitchingProvider(CameraPipeComponentImpl r1, Camera2ControllerComponentImpl r2, int r3) {
                this.cameraPipeComponentImpl = r1;
                this.camera2ControllerComponentImpl = r2;
                this.id = r3;
            }

            @Override // javax.inject.Provider
            public T get() {
                int r1 = this.id;
                switch(r1) {
                    case 0: goto L23;
                    case 1: goto L21;
                    case 2: goto L18;
                    case 3: goto L16;
                    case 4: goto L15;
                    case 5: goto L13;
                    case 6: goto L11;
                    case 7: goto L9;
                    case 8: goto L7;
                    default: goto L5;
                };
            L16:
                Camera2ControllerComponentImpl r0 = this.camera2ControllerComponentImpl;
                return (T) Camera2CaptureSessionsModule_ProvideSessionFactoryFactory.provideSessionFactory(r0.androidMSessionFactoryProvider, r0.androidMHighSpeedSessionFactoryProvider, r0.androidNSessionFactoryProvider, r0.androidPSessionFactoryProvider, r0.androidExtensionSessionFactoryProvider, Camera2ControllerConfig_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(Camera2ControllerComponentImpl.access$300(r0)));
            L18:
                CameraPipeComponentImpl r12 = this.cameraPipeComponentImpl;
                return (T) Camera2ControllerModule_Companion_ProvideCameraStatusMonitorFactory.provideCameraStatusMonitor(r12.provideCameraManagerProvider, r12.provideThreadsProvider.get(), Camera2ControllerConfig_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), this.cameraPipeComponentImpl.provideCameraPipeJobProvider.get());
            L5:
                throw new AssertionError(r1);
            L7:
                return (T) new AndroidExtensionSessionFactory(this.cameraPipeComponentImpl.provideThreadsProvider.get(), Camera2ControllerConfig_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), Camera2ControllerConfig_ProvideStreamGraphFactory.provideStreamGraph(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), this.cameraPipeComponentImpl.camera2MetadataCacheProvider.get(), this.cameraPipeComponentImpl.provideStrictModeProvider.get());
            L9:
                return (T) new AndroidPSessionFactory(this.cameraPipeComponentImpl.provideThreadsProvider.get(), Camera2ControllerConfig_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), Camera2ControllerConfig_ProvideStreamGraphFactory.provideStreamGraph(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)));
            L11:
                return (T) new AndroidNSessionFactory(this.cameraPipeComponentImpl.provideThreadsProvider.get(), Camera2ControllerConfig_ProvideStreamGraphFactory.provideStreamGraph(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), Camera2ControllerConfig_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)));
            L13:
                return (T) new AndroidMHighSpeedSessionFactory(Camera2ControllerConfig_ProvideStreamGraphFactory.provideStreamGraph(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), this.cameraPipeComponentImpl.provideThreadsProvider.get());
            L15:
                return (T) new AndroidMSessionFactory(this.cameraPipeComponentImpl.provideThreadsProvider.get(), Camera2ControllerConfig_ProvideStreamGraphFactory.provideStreamGraph(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), Camera2ControllerConfig_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)));
            L21:
                return (T) Camera2ControllerModule_Companion_ProvideCoroutineScopeFactory.provideCoroutineScope(this.cameraPipeComponentImpl.provideThreadsProvider.get(), this.cameraPipeComponentImpl.provideCameraPipeJobProvider.get());
            L23:
                return (T) new Camera2CameraController(this.camera2ControllerComponentImpl.provideCoroutineScopeProvider.get(), this.cameraPipeComponentImpl.provideThreadsProvider.get(), this.cameraPipeComponentImpl.provideStrictModeProvider.get(), Camera2ControllerConfig_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), Camera2ControllerConfig_ProvideGraphListenerFactory.provideGraphListener(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), Camera2ControllerConfig_ProvideSurfaceGraphFactory.provideSurfaceGraph(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), this.camera2ControllerComponentImpl.provideCameraStatusMonitorProvider.get(), this.camera2ControllerComponentImpl.provideSessionFactoryProvider.get(), this.camera2ControllerComponentImpl.standardCamera2CaptureSequenceProcessorFactory(), this.cameraPipeComponentImpl.pruningCamera2DeviceManagerProvider.get(), this.cameraPipeComponentImpl.provideCameraSurfaceManagerProvider.get(), this.cameraPipeComponentImpl.camera2QuirksProvider.get(), this.cameraPipeComponentImpl.systemTimeSourceProvider.get(), Camera2ControllerConfig_ProvideCameraGraphIdFactory.provideCameraGraphId(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), Camera2ControllerConfig_ProvideShutdownListenerFactory.provideShutdownListener(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), Camera2ControllerConfig_ProvideStreamGraphFactory.provideStreamGraph(Camera2ControllerComponentImpl.access$300(this.camera2ControllerComponentImpl)), this.cameraPipeComponentImpl.concurrentSessionSequencersProvider.get());
            }
        }

        public Camera2ControllerComponentImpl(CameraPipeComponentImpl r1, Camera2ControllerConfig r2) {
            this.camera2ControllerComponentImpl = this;
            this.cameraPipeComponentImpl = r1;
            this.camera2ControllerConfig = r2;
            initialize(r2);
        }

        public static /* synthetic */ Camera2ControllerConfig access$300(Camera2ControllerComponentImpl r0) {
            return r0.camera2ControllerConfig;
        }

        private void initialize(Camera2ControllerConfig r4) {
            this.provideCoroutineScopeProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.camera2ControllerComponentImpl, 1));
            this.provideCameraStatusMonitorProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.camera2ControllerComponentImpl, 2));
            this.androidMSessionFactoryProvider = new SwitchingProvider(this.cameraPipeComponentImpl, this.camera2ControllerComponentImpl, 4);
            this.androidMHighSpeedSessionFactoryProvider = new SwitchingProvider(this.cameraPipeComponentImpl, this.camera2ControllerComponentImpl, 5);
            this.androidNSessionFactoryProvider = new SwitchingProvider(this.cameraPipeComponentImpl, this.camera2ControllerComponentImpl, 6);
            this.androidPSessionFactoryProvider = new SwitchingProvider(this.cameraPipeComponentImpl, this.camera2ControllerComponentImpl, 7);
            this.androidExtensionSessionFactoryProvider = new SwitchingProvider(this.cameraPipeComponentImpl, this.camera2ControllerComponentImpl, 8);
            this.provideSessionFactoryProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.camera2ControllerComponentImpl, 3));
            this.camera2CameraControllerProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.camera2ControllerComponentImpl, 0));
        }

        @Override // androidx.camera.camera2.pipe.config.Camera2ControllerComponent
        public CameraController cameraController() {
            return this.camera2CameraControllerProvider.get();
        }

        public StandardCamera2CaptureSequenceProcessorFactory standardCamera2CaptureSequenceProcessorFactory() {
            return new StandardCamera2CaptureSequenceProcessorFactory(this.cameraPipeComponentImpl.provideThreadsProvider.get(), Camera2ControllerConfig_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(this.camera2ControllerConfig), Camera2ControllerConfig_ProvideStreamGraphFactory.provideStreamGraph(this.camera2ControllerConfig), this.cameraPipeComponentImpl.camera2QuirksProvider.get(), this.cameraPipeComponentImpl.provideStrictModeProvider.get());
        }
    }

    public static final class CameraGraphComponentBuilder implements CameraGraphComponent.Builder {
        private CameraGraphConfigModule cameraGraphConfigModule;
        private final CameraPipeComponentImpl cameraPipeComponentImpl;

        private CameraGraphComponentBuilder(CameraPipeComponentImpl r1) {
            this.cameraPipeComponentImpl = r1;
        }

        public /* synthetic */ CameraGraphComponentBuilder(CameraPipeComponentImpl r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.camera.camera2.pipe.config.CameraGraphComponent.Builder
        public CameraGraphComponent build() {
            Preconditions.checkBuilderRequirement(this.cameraGraphConfigModule, CameraGraphConfigModule.class);
            return new CameraGraphComponentImpl(this.cameraPipeComponentImpl, this.cameraGraphConfigModule);
        }

        @Override // androidx.camera.camera2.pipe.config.CameraGraphComponent.Builder
        public /* bridge */ /* synthetic */ CameraGraphComponent.Builder cameraGraphConfigModule(CameraGraphConfigModule r1) {
            return cameraGraphConfigModule(r1);
        }

        @Override // androidx.camera.camera2.pipe.config.CameraGraphComponent.Builder
        public CameraGraphComponentBuilder cameraGraphConfigModule(CameraGraphConfigModule r1) {
            this.cameraGraphConfigModule = (CameraGraphConfigModule) Preconditions.checkNotNull(r1);
            return this;
        }
    }

    public static final class CameraGraphComponentImpl implements CameraGraphComponent {
        private final CameraGraphComponentImpl cameraGraphComponentImpl;
        private final CameraGraphConfigModule cameraGraphConfigModule;
        Provider<CameraGraphImpl> cameraGraphImplProvider;
        Provider<CameraGraphParametersImpl> cameraGraphParametersImplProvider;
        Provider<CameraGraphRequestListenersImpl> cameraGraphRequestListenersImplProvider;
        private final CameraPipeComponentImpl cameraPipeComponentImpl;
        Provider<Controller3A> controller3AProvider;
        Provider<FrameCaptureQueue> frameCaptureQueueProvider;
        Provider<GraphProcessorImpl> graphProcessorImplProvider;
        Provider<GraphSessionLock> graphSessionLockProvider;
        Provider<GraphState3A> graphState3AProvider;
        Provider<Listener3A> listener3AProvider;
        Provider<CameraBackend> provideCameraBackendProvider;
        Provider<CameraController> provideCameraControllerProvider;
        Provider<CoroutineScope> provideCameraGraphCoroutineScopeProvider;
        Provider<CameraMetadata> provideCameraMetadataProvider;
        Provider<FrameDistributor> provideFrameDistributorProvider;
        Provider<List<Request.Listener>> provideRequestListenersProvider;
        Provider<SurfaceGraph> provideSurfaceGraphProvider;
        Provider<SystemClockOffsets> provideSystemClockOffsetsProvider;
        Provider<StreamGraphImpl> streamGraphImplProvider;

        public static final class SwitchingProvider<T> implements Provider<T> {
            private final CameraGraphComponentImpl cameraGraphComponentImpl;
            private final CameraPipeComponentImpl cameraPipeComponentImpl;
            private final int id;

            public SwitchingProvider(CameraPipeComponentImpl r1, CameraGraphComponentImpl r2, int r3) {
                this.cameraPipeComponentImpl = r1;
                this.cameraGraphComponentImpl = r2;
                this.id = r3;
            }

            @Override // javax.inject.Provider
            public T get() {
                int r1 = this.id;
                switch(r1) {
                    case 0: goto L41;
                    case 1: goto L39;
                    case 2: goto L37;
                    case 3: goto L35;
                    case 4: goto L33;
                    case 5: goto L31;
                    case 6: goto L29;
                    case 7: goto L27;
                    case 8: goto L25;
                    case 9: goto L23;
                    case 10: goto L21;
                    case 11: goto L19;
                    case 12: goto L17;
                    case 13: goto L15;
                    case 14: goto L13;
                    case 15: goto L11;
                    case 16: goto L9;
                    case 17: goto L7;
                    default: goto L5;
                };
            L5:
                throw new AssertionError(r1);
            L7:
                return (T) new GraphState3A();
            L9:
                return (T) new Controller3A(this.cameraGraphComponentImpl.graphProcessorImplProvider.get(), this.cameraGraphComponentImpl.provideCameraMetadataProvider.get(), this.cameraGraphComponentImpl.graphState3AProvider.get(), this.cameraGraphComponentImpl.listener3AProvider.get());
            L11:
                return (T) new CameraGraphRequestListenersImpl(this.cameraGraphComponentImpl.graphSessionLockProvider.get(), this.cameraGraphComponentImpl.graphProcessorImplProvider.get(), this.cameraGraphComponentImpl.provideCameraGraphCoroutineScopeProvider.get());
            L13:
                return (T) SharedCameraGraphModules_Companion_ProvideCameraGraphCoroutineScopeFactory.provideCameraGraphCoroutineScope(this.cameraPipeComponentImpl.provideThreadsProvider.get(), this.cameraPipeComponentImpl.provideCameraPipeJobProvider.get());
            L15:
                return (T) new GraphSessionLock();
            L17:
                return (T) new CameraGraphParametersImpl(this.cameraGraphComponentImpl.graphSessionLockProvider.get(), this.cameraGraphComponentImpl.graphProcessorImplProvider.get(), this.cameraGraphComponentImpl.provideCameraGraphCoroutineScopeProvider.get());
            L19:
                return (T) SharedCameraGraphModules_Companion_ProvideSystemClockOffsetsFactory.provideSystemClockOffsets();
            L21:
                return (T) new FrameCaptureQueue();
            L23:
                return (T) SharedCameraGraphModules_Companion_ProvideSurfaceGraphFactory.provideSurfaceGraph(this.cameraGraphComponentImpl.streamGraphImplProvider.get(), this.cameraGraphComponentImpl.provideCameraControllerProvider, this.cameraPipeComponentImpl.provideCameraSurfaceManagerProvider.get());
            L25:
                return (T) InternalCameraGraphModules_Companion_ProvideCameraControllerFactory.provideCameraController(CameraGraphConfigModule_ProvideCameraGraphIdFactory.provideCameraGraphId(CameraGraphComponentImpl.access$100(this.cameraGraphComponentImpl)), CameraGraphConfigModule_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(CameraGraphComponentImpl.access$100(this.cameraGraphComponentImpl)), this.cameraGraphComponentImpl.provideCameraBackendProvider.get(), this.cameraPipeComponentImpl.provideCameraContextProvider.get(), this.cameraGraphComponentImpl.graphProcessorImplProvider.get(), this.cameraGraphComponentImpl.streamGraphImplProvider.get(), this.cameraGraphComponentImpl.provideSurfaceGraphProvider.get());
            L27:
                return (T) new StreamGraphImpl(this.cameraGraphComponentImpl.provideCameraMetadataProvider.get(), CameraGraphConfigModule_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(CameraGraphComponentImpl.access$100(this.cameraGraphComponentImpl)), this.cameraPipeComponentImpl.imageSources(), this.cameraGraphComponentImpl.provideCameraControllerProvider);
            L29:
                return (T) SharedCameraGraphModules_Companion_ProvideFrameDistributorFactory.provideFrameDistributor(this.cameraGraphComponentImpl.streamGraphImplProvider.get(), this.cameraGraphComponentImpl.frameCaptureQueueProvider.get(), this.cameraGraphComponentImpl.provideCameraMetadataProvider.get(), this.cameraGraphComponentImpl.provideSystemClockOffsetsProvider.get());
            L31:
                return (T) SharedCameraGraphModules_Companion_ProvideRequestListenersFactory.provideRequestListeners(CameraGraphConfigModule_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(CameraGraphComponentImpl.access$100(this.cameraGraphComponentImpl)), this.cameraGraphComponentImpl.listener3AProvider.get(), this.cameraGraphComponentImpl.provideFrameDistributorProvider.get());
            L33:
                return (T) new Listener3A();
            L35:
                return (T) new GraphProcessorImpl(this.cameraPipeComponentImpl.provideThreadsProvider.get(), CameraGraphConfigModule_ProvideCameraGraphIdFactory.provideCameraGraphId(CameraGraphComponentImpl.access$100(this.cameraGraphComponentImpl)), CameraGraphConfigModule_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(CameraGraphComponentImpl.access$100(this.cameraGraphComponentImpl)), this.cameraGraphComponentImpl.listener3AProvider.get(), this.cameraGraphComponentImpl.provideRequestListenersProvider.get(), this.cameraPipeComponentImpl.camera2QuirksProvider.get());
            L37:
                return (T) InternalCameraGraphModules_Companion_ProvideCameraBackendFactory.provideCameraBackend(this.cameraPipeComponentImpl.provideCameraBackendsProvider.get(), CameraGraphConfigModule_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(CameraGraphComponentImpl.access$100(this.cameraGraphComponentImpl)), this.cameraPipeComponentImpl.provideCameraContextProvider.get());
            L39:
                return (T) InternalCameraGraphModules_Companion_ProvideCameraMetadataFactory.provideCameraMetadata(CameraGraphConfigModule_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(CameraGraphComponentImpl.access$100(this.cameraGraphComponentImpl)), this.cameraGraphComponentImpl.provideCameraBackendProvider.get());
            L41:
                return (T) new CameraGraphImpl(CameraGraphConfigModule_ProvideCameraGraphConfigFactory.provideCameraGraphConfig(CameraGraphComponentImpl.access$100(this.cameraGraphComponentImpl)), this.cameraGraphComponentImpl.provideCameraMetadataProvider.get(), this.cameraGraphComponentImpl.graphProcessorImplProvider.get(), this.cameraGraphComponentImpl.graphProcessorImplProvider.get(), this.cameraGraphComponentImpl.streamGraphImplProvider.get(), this.cameraGraphComponentImpl.provideSurfaceGraphProvider.get(), this.cameraGraphComponentImpl.provideCameraControllerProvider.get(), this.cameraGraphComponentImpl.provideFrameDistributorProvider.get(), this.cameraGraphComponentImpl.frameCaptureQueueProvider.get(), this.cameraPipeComponentImpl.audioRestrictionControllerImplProvider.get(), CameraGraphConfigModule_ProvideCameraGraphIdFactory.provideCameraGraphId(CameraGraphComponentImpl.access$100(this.cameraGraphComponentImpl)), this.cameraGraphComponentImpl.cameraGraphParametersImplProvider.get(), this.cameraGraphComponentImpl.cameraGraphRequestListenersImplProvider.get(), this.cameraGraphComponentImpl.graphSessionLockProvider.get(), this.cameraGraphComponentImpl.provideCameraGraphCoroutineScopeProvider.get(), this.cameraGraphComponentImpl.controller3AProvider.get());
            }
        }

        public CameraGraphComponentImpl(CameraPipeComponentImpl r1, CameraGraphConfigModule r2) {
            this.cameraGraphComponentImpl = this;
            this.cameraPipeComponentImpl = r1;
            this.cameraGraphConfigModule = r2;
            initialize(r2);
        }

        public static /* synthetic */ CameraGraphConfigModule access$100(CameraGraphComponentImpl r0) {
            return r0.cameraGraphConfigModule;
        }

        private void initialize(CameraGraphConfigModule r5) {
            this.provideCameraBackendProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 2));
            this.provideCameraMetadataProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 1));
            this.listener3AProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 4));
            this.graphProcessorImplProvider = new DelegateFactory();
            this.streamGraphImplProvider = new DelegateFactory();
            this.provideCameraControllerProvider = new DelegateFactory();
            this.provideSurfaceGraphProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 9));
            DelegateFactory.setDelegate(this.provideCameraControllerProvider, DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 8)));
            DelegateFactory.setDelegate(this.streamGraphImplProvider, DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 7)));
            this.frameCaptureQueueProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 10));
            this.provideSystemClockOffsetsProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 11));
            this.provideFrameDistributorProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 6));
            this.provideRequestListenersProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 5));
            DelegateFactory.setDelegate(this.graphProcessorImplProvider, DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 3)));
            this.graphSessionLockProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 13));
            this.provideCameraGraphCoroutineScopeProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 14));
            this.cameraGraphParametersImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 12));
            this.cameraGraphRequestListenersImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 15));
            this.graphState3AProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 17));
            this.controller3AProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 16));
            this.cameraGraphImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, this.cameraGraphComponentImpl, 0));
        }

        @Override // androidx.camera.camera2.pipe.config.CameraGraphComponent
        public CameraGraph cameraGraph() {
            return this.cameraGraphImplProvider.get();
        }
    }

    public static final class CameraPipeComponentImpl implements CameraPipeComponent {
        Provider<AudioRestrictionControllerImpl> audioRestrictionControllerImplProvider;
        Provider<Camera2Backend> camera2BackendProvider;
        Provider<Camera2DeviceCache> camera2DeviceCacheProvider;
        Provider<Camera2DeviceCloserImpl> camera2DeviceCloserImplProvider;
        Provider<Camera2ErrorProcessor> camera2ErrorProcessorProvider;
        Provider<Camera2MetadataCache> camera2MetadataCacheProvider;
        Provider<Camera2Quirks> camera2QuirksProvider;
        Provider<CameraDevicesImpl> cameraDevicesImplProvider;
        private final CameraPipeComponentImpl cameraPipeComponentImpl;
        private final CameraPipeConfigModule cameraPipeConfigModule;
        Provider<CameraPipeLifetime> cameraPipeLifetimeProvider;
        Provider<ConcurrentSessionSequencers> concurrentSessionSequencersProvider;
        Provider<Permissions> permissionsProvider;
        Provider<CameraBackends> provideCameraBackendsProvider;
        Provider<CameraContext> provideCameraContextProvider;
        Provider<CameraDeviceSetupCompatFactory> provideCameraDeviceSetupCompatFactoryProvider;
        Provider<CameraManager> provideCameraManagerProvider;
        Provider<Job> provideCameraPipeJobProvider;
        Provider<CameraSurfaceManager> provideCameraSurfaceManagerProvider;
        Provider<DevicePolicyManagerWrapper> provideDevicePolicyManagerWrapperProvider;
        Provider<PackageManager> providePackageManagerProvider;
        Provider<StrictMode> provideStrictModeProvider;
        Provider<Threads> provideThreadsProvider;
        Provider<PruningCamera2DeviceManager> pruningCamera2DeviceManagerProvider;
        Provider<RetryingCameraStateOpenerImpl> retryingCameraStateOpenerImplProvider;
        Provider<SystemTimeSource> systemTimeSourceProvider;
        private final ThreadConfigModule threadConfigModule;

        public static final class SwitchingProvider<T> implements Provider<T> {
            private final CameraPipeComponentImpl cameraPipeComponentImpl;
            private final int id;

            public SwitchingProvider(CameraPipeComponentImpl r1, int r2) {
                this.cameraPipeComponentImpl = r1;
                this.id = r2;
            }

            @Override // javax.inject.Provider
            public T get() {
                int r0 = this.id;
                switch(r0) {
                    case 0: goto L53;
                    case 1: goto L51;
                    case 2: goto L49;
                    case 3: goto L46;
                    case 4: goto L45;
                    case 5: goto L43;
                    case 6: goto L40;
                    case 7: goto L39;
                    case 8: goto L37;
                    case 9: goto L35;
                    case 10: goto L33;
                    case 11: goto L31;
                    case 12: goto L29;
                    case 13: goto L27;
                    case 14: goto L25;
                    case 15: goto L23;
                    case 16: goto L21;
                    case 17: goto L19;
                    case 18: goto L17;
                    case 19: goto L15;
                    case 20: goto L13;
                    case 21: goto L11;
                    case 22: goto L9;
                    case 23: goto L7;
                    default: goto L5;
                };
            L40:
                CameraPipeComponentImpl r1 = this.cameraPipeComponentImpl;
                Provider<CameraManager> r12 = r1.provideCameraManagerProvider;
                Threads r2 = r1.provideThreadsProvider.get();
                Context r3 = this.cameraPipeComponentImpl.cameraPipeContextContext();
                PackageManager r4 = this.cameraPipeComponentImpl.providePackageManagerProvider.get();
                Camera2ErrorProcessor r5 = this.cameraPipeComponentImpl.camera2ErrorProcessorProvider.get();
                CameraPipeComponentImpl r6 = this.cameraPipeComponentImpl;
                return (T) new Camera2DeviceCache(r12, r2, r3, r4, r5, r6.provideCameraDeviceSetupCompatFactoryProvider, r6.cameraPipeLifetimeProvider.get(), this.cameraPipeComponentImpl.provideCameraPipeJobProvider.get());
            L46:
                CameraPipe.Config r02 = CameraPipeConfigModule_ProvideCameraPipeConfigFactory.provideCameraPipeConfig(CameraPipeComponentImpl.access$600(this.cameraPipeComponentImpl));
                CameraPipeComponentImpl r13 = this.cameraPipeComponentImpl;
                return (T) CameraPipeModule_Companion_ProvideCameraBackendsFactory.provideCameraBackends(r02, r13.camera2BackendProvider, r13.cameraPipeContextContext(), this.cameraPipeComponentImpl.provideThreadsProvider.get(), this.cameraPipeComponentImpl.cameraPipeLifetimeProvider.get());
            L5:
                throw new AssertionError(r0);
            L7:
                return (T) new ConcurrentSessionSequencers();
            L9:
                return (T) CameraPipeModule_Companion_ProvideCameraSurfaceManagerFactory.provideCameraSurfaceManager();
            L11:
                return (T) CameraPipeModule_Companion_ProvideCameraContextFactory.provideCameraContext(this.cameraPipeComponentImpl.cameraPipeContextContext(), this.cameraPipeComponentImpl.provideThreadsProvider.get(), this.cameraPipeComponentImpl.provideCameraBackendsProvider.get());
            L13:
                return (T) new Camera2DeviceCloserImpl(this.cameraPipeComponentImpl.provideThreadsProvider.get(), this.cameraPipeComponentImpl.camera2QuirksProvider.get(), this.cameraPipeComponentImpl.retryingCameraStateOpenerImplProvider.get());
            L15:
                return (T) new AudioRestrictionControllerImpl(this.cameraPipeComponentImpl.provideThreadsProvider.get(), this.cameraPipeComponentImpl.cameraPipeLifetimeProvider.get(), this.cameraPipeComponentImpl.provideCameraPipeJobProvider.get());
            L17:
                return (T) CameraPipeModule_Companion_ProvideDevicePolicyManagerWrapperFactory.provideDevicePolicyManagerWrapper(this.cameraPipeComponentImpl.cameraPipeContextContext());
            L19:
                return (T) CameraPipeModule_Companion_ProvideStrictModeFactory.provideStrictMode(CameraPipeConfigModule_ProvideCameraPipeFlagsFactory.provideCameraPipeFlags(CameraPipeComponentImpl.access$600(this.cameraPipeComponentImpl)));
            L21:
                return (T) new Camera2Quirks(this.cameraPipeComponentImpl.camera2MetadataCacheProvider.get(), this.cameraPipeComponentImpl.provideStrictModeProvider.get());
            L23:
                return (T) new RetryingCameraStateOpenerImpl(this.cameraPipeComponentImpl.cameraStateOpener(), this.cameraPipeComponentImpl.camera2ErrorProcessorProvider.get(), this.cameraPipeComponentImpl.camera2CameraAvailabilityMonitor(), this.cameraPipeComponentImpl.systemTimeSourceProvider.get(), this.cameraPipeComponentImpl.provideDevicePolicyManagerWrapperProvider.get(), this.cameraPipeComponentImpl.audioRestrictionControllerImplProvider.get(), this.cameraPipeComponentImpl.cameraInteropConfig(), this.cameraPipeComponentImpl.provideThreadsProvider.get());
            L25:
                return (T) new PruningCamera2DeviceManager(this.cameraPipeComponentImpl.permissionsProvider.get(), this.cameraPipeComponentImpl.retryingCameraStateOpenerImplProvider.get(), this.cameraPipeComponentImpl.camera2DeviceCloserImplProvider.get(), this.cameraPipeComponentImpl.camera2ErrorProcessorProvider.get(), this.cameraPipeComponentImpl.provideThreadsProvider.get());
            L27:
                return (T) new SystemTimeSource();
            L29:
                return (T) new Permissions(this.cameraPipeComponentImpl.cameraPipeContextContext());
            L31:
                return (T) new Camera2MetadataCache(this.cameraPipeComponentImpl.cameraPipeContextContext(), this.cameraPipeComponentImpl.provideThreadsProvider.get(), this.cameraPipeComponentImpl.permissionsProvider.get(), this.cameraPipeComponentImpl.cameraMetadataConfig(), this.cameraPipeComponentImpl.systemTimeSourceProvider.get());
            L33:
                return (T) CameraPipeModule_Companion_ProvideCameraDeviceSetupCompatFactoryFactory.provideCameraDeviceSetupCompatFactory(this.cameraPipeComponentImpl.cameraPipeContextContext());
            L35:
                return (T) new Camera2ErrorProcessor();
            L37:
                return (T) CameraPipeModule_Companion_ProvidePackageManagerFactory.providePackageManager(this.cameraPipeComponentImpl.cameraPipeContextContext());
            L39:
                return (T) CameraPipeModule_Companion_ProvideCameraManagerFactory.provideCameraManager(this.cameraPipeComponentImpl.cameraPipeContextContext());
            L43:
                return (T) ThreadConfigModule_ProvideThreadsFactory.provideThreads(CameraPipeComponentImpl.access$800(this.cameraPipeComponentImpl), this.cameraPipeComponentImpl.cameraPipeLifetimeProvider.get(), this.cameraPipeComponentImpl.provideCameraPipeJobProvider.get());
            L45:
                return (T) new Camera2Backend(this.cameraPipeComponentImpl.provideThreadsProvider.get(), this.cameraPipeComponentImpl.camera2DeviceCacheProvider.get(), this.cameraPipeComponentImpl.camera2MetadataCacheProvider.get(), this.cameraPipeComponentImpl.pruningCamera2DeviceManagerProvider.get(), new Camera2ControllerComponentBuilder(this.cameraPipeComponentImpl, null), this.cameraPipeComponentImpl.cameraPipeContextContext());
            L49:
                return (T) new CameraDevicesImpl(this.cameraPipeComponentImpl.provideCameraBackendsProvider.get());
            L51:
                return (T) CameraPipeModule_Companion_ProvideCameraPipeJobFactory.provideCameraPipeJob();
            L53:
                return (T) new CameraPipeLifetime(this.cameraPipeComponentImpl.provideCameraPipeJobProvider.get());
            }
        }

        public CameraPipeComponentImpl(CameraPipeConfigModule r1, ThreadConfigModule r2) {
            this.cameraPipeComponentImpl = this;
            this.cameraPipeConfigModule = r1;
            this.threadConfigModule = r2;
            initialize(r1, r2);
        }

        public static /* synthetic */ CameraPipeConfigModule access$600(CameraPipeComponentImpl r0) {
            return r0.cameraPipeConfigModule;
        }

        public static /* synthetic */ ThreadConfigModule access$800(CameraPipeComponentImpl r0) {
            return r0.threadConfigModule;
        }

        private void initialize(CameraPipeConfigModule r2, ThreadConfigModule r3) {
            this.provideCameraPipeJobProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 1));
            this.cameraPipeLifetimeProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 0));
            this.provideThreadsProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 5));
            this.provideCameraManagerProvider = SingleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 7));
            this.providePackageManagerProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 8));
            this.camera2ErrorProcessorProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 9));
            this.provideCameraDeviceSetupCompatFactoryProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 10));
            this.camera2DeviceCacheProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 6));
            this.permissionsProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 12));
            this.systemTimeSourceProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 13));
            this.camera2MetadataCacheProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 11));
            this.provideStrictModeProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 17));
            this.camera2QuirksProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 16));
            this.provideDevicePolicyManagerWrapperProvider = SingleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 18));
            this.audioRestrictionControllerImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 19));
            this.retryingCameraStateOpenerImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 15));
            this.camera2DeviceCloserImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 20));
            this.pruningCamera2DeviceManagerProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 14));
            this.camera2BackendProvider = new SwitchingProvider(this.cameraPipeComponentImpl, 4);
            this.provideCameraBackendsProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 3));
            this.cameraDevicesImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 2));
            this.provideCameraContextProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 21));
            this.provideCameraSurfaceManagerProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 22));
            this.concurrentSessionSequencersProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraPipeComponentImpl, 23));
        }

        public Camera2CameraAvailabilityMonitor camera2CameraAvailabilityMonitor() {
            return new Camera2CameraAvailabilityMonitor(this.provideCameraManagerProvider, this.provideThreadsProvider.get(), this.provideCameraPipeJobProvider.get());
        }

        public Camera2CameraOpener camera2CameraOpener() {
            return new Camera2CameraOpener(this.provideCameraManagerProvider, this.provideThreadsProvider.get());
        }

        @Override // androidx.camera.camera2.pipe.config.CameraPipeComponent
        public CameraBackends cameraBackends() {
            return this.provideCameraBackendsProvider.get();
        }

        @Override // androidx.camera.camera2.pipe.config.CameraPipeComponent
        public CameraContext cameraContext() {
            return this.provideCameraContextProvider.get();
        }

        @Override // androidx.camera.camera2.pipe.config.CameraPipeComponent
        public CameraGraphComponent.Builder cameraGraphComponentBuilder() {
            return new CameraGraphComponentBuilder(this.cameraPipeComponentImpl, null);
        }

        public CameraPipe.CameraInteropConfig cameraInteropConfig() {
            CameraPipeConfigModule r1 = this.cameraPipeConfigModule;
            return CameraPipeConfigModule_ProvideCameraInteropConfigFactory.provideCameraInteropConfig(r1, CameraPipeConfigModule_ProvideCameraPipeConfigFactory.provideCameraPipeConfig(r1));
        }

        public CameraPipe.CameraMetadataConfig cameraMetadataConfig() {
            return CameraPipeModule_Companion_ProvideCameraMetadataConfigFactory.provideCameraMetadataConfig(CameraPipeConfigModule_ProvideCameraPipeConfigFactory.provideCameraPipeConfig(this.cameraPipeConfigModule));
        }

        public Context cameraPipeContextContext() {
            return CameraPipeModule_Companion_ProvideContextFactory.provideContext(CameraPipeConfigModule_ProvideCameraPipeConfigFactory.provideCameraPipeConfig(this.cameraPipeConfigModule));
        }

        @Override // androidx.camera.camera2.pipe.config.CameraPipeComponent
        public CameraPipeLifetime cameraPipeLifetime() {
            return this.cameraPipeLifetimeProvider.get();
        }

        public CameraStateOpener cameraStateOpener() {
            return new CameraStateOpener(camera2CameraOpener(), this.camera2MetadataCacheProvider.get(), this.camera2ErrorProcessorProvider.get(), this.camera2QuirksProvider.get(), this.systemTimeSourceProvider.get(), cameraInteropConfig(), this.provideThreadsProvider.get());
        }

        @Override // androidx.camera.camera2.pipe.config.CameraPipeComponent
        public CameraSurfaceManager cameraSurfaceManager() {
            return this.provideCameraSurfaceManagerProvider.get();
        }

        @Override // androidx.camera.camera2.pipe.config.CameraPipeComponent
        public CameraDevices cameras() {
            return this.cameraDevicesImplProvider.get();
        }

        public ImageReaderImageSources imageReaderImageSources() {
            return new ImageReaderImageSources(this.provideThreadsProvider.get(), CameraPipeConfigModule_ProvideCameraPipeConfigFactory.provideCameraPipeConfig(this.cameraPipeConfigModule));
        }

        public ImageSources imageSources() {
            return CameraPipeModule_Companion_ConfigureImageSourcesFactory.configureImageSources(imageReaderImageSources(), CameraPipeConfigModule_ProvideCameraPipeConfigFactory.provideCameraPipeConfig(this.cameraPipeConfigModule));
        }
    }

    public static Builder builder() {
        return new Builder(null);
    }
}
