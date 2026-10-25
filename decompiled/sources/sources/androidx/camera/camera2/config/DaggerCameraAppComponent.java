package androidx.camera.camera2.config;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.camera.camera2.adapter.CameraControlAdapter;
import androidx.camera.camera2.adapter.CameraControlStateAdapter;
import androidx.camera.camera2.adapter.CameraInfoAdapter;
import androidx.camera.camera2.adapter.CameraInternalAdapter;
import androidx.camera.camera2.adapter.CameraStateAdapter;
import androidx.camera.camera2.adapter.CaptureConfigAdapter;
import androidx.camera.camera2.adapter.SessionConfigAdapter;
import androidx.camera.camera2.adapter.ZslControl;
import androidx.camera.camera2.compat.Camera2CameraControlCompatImpl;
import androidx.camera.camera2.compat.EvCompImpl;
import androidx.camera.camera2.compat.StreamConfigurationMapCompat;
import androidx.camera.camera2.compat.ZoomCompat;
import androidx.camera.camera2.compat.ZoomCompat_Bindings_Companion_ProvideZoomCompatFactory;
import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.compat.workaround.AutoFlashAEModeDisabler;
import androidx.camera.camera2.compat.workaround.AutoFlashAEModeDisabler_Bindings_Companion_ProvideAEModeDisablerFactory;
import androidx.camera.camera2.compat.workaround.CapturePipelineTorchCorrection;
import androidx.camera.camera2.compat.workaround.InactiveSurfaceCloser;
import androidx.camera.camera2.compat.workaround.InactiveSurfaceCloser_Bindings_Companion_ProvideInactiveSurfaceCloserFactory;
import androidx.camera.camera2.compat.workaround.MeteringRegionCorrection;
import androidx.camera.camera2.compat.workaround.MeteringRegionCorrection_Bindings_Companion_ProvideMeteringRegionCorrectionFactory;
import androidx.camera.camera2.compat.workaround.OutputSizesCorrector;
import androidx.camera.camera2.compat.workaround.TemplateParamsOverride;
import androidx.camera.camera2.compat.workaround.TemplateParamsOverride_Bindings_Companion_ProvideTemplateParamsOverrideFactory;
import androidx.camera.camera2.compat.workaround.UseFlashModeTorchFor3aUpdate;
import androidx.camera.camera2.compat.workaround.UseFlashModeTorchFor3aUpdate_Bindings_Companion_ProvideUseFlashModeTorchFor3aUpdateFactory;
import androidx.camera.camera2.compat.workaround.UseTorchAsFlash;
import androidx.camera.camera2.compat.workaround.UseTorchAsFlash_Bindings_Companion_ProvideUseTorchAsFlashFactory;
import androidx.camera.camera2.config.CameraAppComponent;
import androidx.camera.camera2.config.CameraComponent;
import androidx.camera.camera2.config.UseCaseCameraComponent;
import androidx.camera.camera2.impl.CameraCallbackMap;
import androidx.camera.camera2.impl.CameraGraphConfigProvider;
import androidx.camera.camera2.impl.CameraPipeCameraProperties;
import androidx.camera.camera2.impl.CapturePipeline;
import androidx.camera.camera2.impl.CapturePipelineImpl;
import androidx.camera.camera2.impl.ComboRequestListener;
import androidx.camera.camera2.impl.DeferredUseCaseCameraRequestControl;
import androidx.camera.camera2.impl.DisplayInfoManager;
import androidx.camera.camera2.impl.EvCompControl;
import androidx.camera.camera2.impl.FlashControl;
import androidx.camera.camera2.impl.FocusMeteringControl;
import androidx.camera.camera2.impl.LowLightBoostControl;
import androidx.camera.camera2.impl.State3AControl;
import androidx.camera.camera2.impl.StillCaptureRequestControl;
import androidx.camera.camera2.impl.TorchControl;
import androidx.camera.camera2.impl.UseCaseCamera;
import androidx.camera.camera2.impl.UseCaseCameraControl;
import androidx.camera.camera2.impl.UseCaseCameraImpl;
import androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl;
import androidx.camera.camera2.impl.UseCaseCameraState;
import androidx.camera.camera2.impl.UseCaseManager;
import androidx.camera.camera2.impl.UseCaseSurfaceManager;
import androidx.camera.camera2.impl.UseCaseThreads;
import androidx.camera.camera2.impl.VideoUsageControl;
import androidx.camera.camera2.impl.ZoomControl;
import androidx.camera.camera2.internal.IntrinsicZoomCalculatorImpl;
import androidx.camera.camera2.interop.Camera2CameraControl;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.internal.StreamSpecsCalculator;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class DaggerCameraAppComponent {

    /* renamed from: androidx.camera.camera2.config.DaggerCameraAppComponent$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder implements CameraAppComponent.Builder {
        private CameraAppConfig cameraAppConfig;

        private Builder() {
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        @Override // androidx.camera.camera2.config.CameraAppComponent.Builder
        public CameraAppComponent build() {
            Preconditions.checkBuilderRequirement(this.cameraAppConfig, CameraAppConfig.class);
            return new CameraAppComponentImpl(this.cameraAppConfig);
        }

        @Override // androidx.camera.camera2.config.CameraAppComponent.Builder
        public /* bridge */ /* synthetic */ CameraAppComponent.Builder config(CameraAppConfig r1) {
            return config(r1);
        }

        @Override // androidx.camera.camera2.config.CameraAppComponent.Builder
        public Builder config(CameraAppConfig r1) {
            this.cameraAppConfig = (CameraAppConfig) Preconditions.checkNotNull(r1);
            return this;
        }
    }

    public static final class CameraAppComponentImpl implements CameraAppComponent {
        private final CameraAppComponentImpl cameraAppComponentImpl;
        private final CameraAppConfig cameraAppConfig;

        public CameraAppComponentImpl(CameraAppConfig r1) {
            this.cameraAppComponentImpl = this;
            this.cameraAppConfig = r1;
        }

        public static /* synthetic */ CameraAppConfig access$200(CameraAppComponentImpl r0) {
            return r0.cameraAppConfig;
        }

        @Override // androidx.camera.camera2.config.CameraAppComponent
        public CameraComponent.Builder cameraBuilder() {
            return new CameraComponentBuilder(this.cameraAppComponentImpl, null);
        }

        public DisplayInfoManager displayInfoManager() {
            CameraAppConfig r1 = this.cameraAppConfig;
            return CameraAppConfig_ProvideDisplayInfoManagerFactory.provideDisplayInfoManager(r1, CameraAppConfig_ProvideContextFactory.provideContext(r1));
        }

        @Override // androidx.camera.camera2.config.CameraAppComponent
        public CameraDevices getCameraDevices() {
            return CameraAppModule_Companion_ProvideCameraDevicesFactory.provideCameraDevices(CameraAppConfig_ProvideCameraPipeFactory.provideCameraPipe(this.cameraAppConfig));
        }

        @Override // androidx.camera.camera2.config.CameraAppComponent
        public CameraPipe getCameraPipe() {
            return CameraAppConfig_ProvideCameraPipeFactory.provideCameraPipe(this.cameraAppConfig);
        }
    }

    public static final class CameraComponentBuilder implements CameraComponent.Builder {
        private final CameraAppComponentImpl cameraAppComponentImpl;
        private CameraConfig cameraConfig;
        private StreamSpecsCalculator streamSpecsCalculator;

        private CameraComponentBuilder(CameraAppComponentImpl r1) {
            this.cameraAppComponentImpl = r1;
        }

        public /* synthetic */ CameraComponentBuilder(CameraAppComponentImpl r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.camera.camera2.config.CameraComponent.Builder
        public CameraComponent build() {
            Preconditions.checkBuilderRequirement(this.cameraConfig, CameraConfig.class);
            Preconditions.checkBuilderRequirement(this.streamSpecsCalculator, StreamSpecsCalculator.class);
            return new CameraComponentImpl(this.cameraAppComponentImpl, this.cameraConfig, this.streamSpecsCalculator);
        }

        @Override // androidx.camera.camera2.config.CameraComponent.Builder
        public /* bridge */ /* synthetic */ CameraComponent.Builder config(CameraConfig r1) {
            return config(r1);
        }

        @Override // androidx.camera.camera2.config.CameraComponent.Builder
        public CameraComponentBuilder config(CameraConfig r1) {
            this.cameraConfig = (CameraConfig) Preconditions.checkNotNull(r1);
            return this;
        }

        @Override // androidx.camera.camera2.config.CameraComponent.Builder
        public /* bridge */ /* synthetic */ CameraComponent.Builder streamSpecsCalculator(StreamSpecsCalculator r1) {
            return streamSpecsCalculator(r1);
        }

        @Override // androidx.camera.camera2.config.CameraComponent.Builder
        public CameraComponentBuilder streamSpecsCalculator(StreamSpecsCalculator r1) {
            this.streamSpecsCalculator = (StreamSpecsCalculator) Preconditions.checkNotNull(r1);
            return this;
        }
    }

    public static final class CameraComponentImpl implements CameraComponent {
        Provider<Camera2CameraControlCompatImpl> camera2CameraControlCompatImplProvider;
        private final CameraAppComponentImpl cameraAppComponentImpl;
        Provider<CameraCallbackMap> cameraCallbackMapProvider;
        private final CameraComponentImpl cameraComponentImpl;
        private final CameraConfig cameraConfig;
        Provider<CameraControlAdapter> cameraControlAdapterProvider;
        Provider<CameraControlStateAdapter> cameraControlStateAdapterProvider;
        Provider<CameraGraphConfigProvider> cameraGraphConfigProvider;
        Provider<CameraInfoAdapter> cameraInfoAdapterProvider;
        Provider<CameraInternalAdapter> cameraInternalAdapterProvider;
        Provider<CameraPipeCameraProperties> cameraPipeCameraPropertiesProvider;
        Provider<CameraQuirks> cameraQuirksProvider;
        Provider<CameraStateAdapter> cameraStateAdapterProvider;
        Provider<ComboRequestListener> comboRequestListenerProvider;
        Provider<EvCompControl> evCompControlProvider;
        Provider<EvCompImpl> evCompImplProvider;
        Provider<FlashControl> flashControlProvider;
        Provider<FocusMeteringControl> focusMeteringControlProvider;
        Provider<IntrinsicZoomCalculatorImpl> intrinsicZoomCalculatorImplProvider;
        Provider<LowLightBoostControl> lowLightBoostControlProvider;
        Provider<OutputSizesCorrector> outputSizesCorrectorProvider;
        Provider<Camera2CameraControl> provideCamera2CameraControlProvider;
        Provider<String> provideCameraIdStringProvider;
        Provider<CameraMetadata> provideCameraMetadataProvider;
        Provider<EncoderProfilesProvider> provideEncoderProfilesProvider;
        Provider<StreamConfigurationMap> provideStreamConfigurationMapProvider;
        Provider<UseCaseThreads> provideUseCaseThreadsProvider;
        Provider<ZslControl> provideZslControlProvider;
        Provider<State3AControl> state3AControlProvider;
        Provider<StillCaptureRequestControl> stillCaptureRequestControlProvider;
        Provider<StreamConfigurationMapCompat> streamConfigurationMapCompatProvider;
        private final StreamSpecsCalculator streamSpecsCalculator;
        Provider<TorchControl> torchControlProvider;
        Provider<UseCaseManager> useCaseManagerProvider;
        Provider<VideoUsageControl> videoUsageControlProvider;
        Provider<ZoomControl> zoomControlProvider;

        public static final class SwitchingProvider<T> implements Provider<T> {
            private final CameraAppComponentImpl cameraAppComponentImpl;
            private final CameraComponentImpl cameraComponentImpl;
            private final int id;

            public SwitchingProvider(CameraAppComponentImpl r1, CameraComponentImpl r2, int r3) {
                this.cameraAppComponentImpl = r1;
                this.cameraComponentImpl = r2;
                this.id = r3;
            }

            @Override // javax.inject.Provider
            public T get() {
                int r1 = this.id;
                switch(r1) {
                    case 0: goto L69;
                    case 1: goto L66;
                    case 2: goto L65;
                    case 3: goto L63;
                    case 4: goto L61;
                    case 5: goto L59;
                    case 6: goto L57;
                    case 7: goto L55;
                    case 8: goto L53;
                    case 9: goto L51;
                    case 10: goto L49;
                    case 11: goto L47;
                    case 12: goto L45;
                    case 13: goto L43;
                    case 14: goto L41;
                    case 15: goto L39;
                    case 16: goto L37;
                    case 17: goto L35;
                    case 18: goto L33;
                    case 19: goto L31;
                    case 20: goto L29;
                    case 21: goto L27;
                    case 22: goto L25;
                    case 23: goto L23;
                    case 24: goto L21;
                    case 25: goto L19;
                    case 26: goto L17;
                    case 27: goto L15;
                    case 28: goto L13;
                    case 29: goto L11;
                    case 30: goto L9;
                    case 31: goto L7;
                    default: goto L5;
                };
            L66:
                CameraPipe r2 = CameraAppConfig_ProvideCameraPipeFactory.provideCameraPipe(CameraAppComponentImpl.access$200(this.cameraAppComponentImpl));
                CameraCoordinator r3 = CameraAppConfig_ProvideCameraCoordinatorFactory.provideCameraCoordinator(CameraAppComponentImpl.access$200(this.cameraAppComponentImpl));
                UseCaseCameraComponentBuilder r4 = new UseCaseCameraComponentBuilder(this.cameraAppComponentImpl, this.cameraComponentImpl, null);
                ZslControl r5 = this.cameraComponentImpl.provideZslControlProvider.get();
                LowLightBoostControl r6 = this.cameraComponentImpl.lowLightBoostControlProvider.get();
                Set<UseCaseCameraControl> r7 = this.cameraComponentImpl.setOfUseCaseCameraControl();
                Camera2CameraControl r8 = this.cameraComponentImpl.provideCamera2CameraControlProvider.get();
                CameraStateAdapter r9 = this.cameraComponentImpl.cameraStateAdapterProvider.get();
                CameraComponentImpl r10 = this.cameraComponentImpl;
                return (T) new UseCaseManager(r2, r3, r4, r5, r6, r7, r8, r9, r10.cameraInternalAdapterProvider, r10.provideUseCaseThreadsProvider, r10.cameraInfoAdapterProvider, r10.provideEncoderProfilesProvider.get(), this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get(), CameraAppConfig_ProvideCameraXConfigFactory.provideCameraXConfig(CameraAppComponentImpl.access$200(this.cameraAppComponentImpl)), this.cameraComponentImpl.cameraGraphConfigProvider.get(), CameraAppConfig_ProvideContextFactory.provideContext(CameraAppComponentImpl.access$200(this.cameraAppComponentImpl)), this.cameraAppComponentImpl.displayInfoManager());
            L5:
                throw new AssertionError(r1);
            L7:
                return (T) new CameraControlAdapter(this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get(), this.cameraComponentImpl.evCompControlProvider.get(), this.cameraComponentImpl.flashControlProvider.get(), this.cameraComponentImpl.focusMeteringControlProvider.get(), this.cameraComponentImpl.stillCaptureRequestControlProvider.get(), this.cameraComponentImpl.torchControlProvider.get(), this.cameraComponentImpl.lowLightBoostControlProvider.get(), this.cameraComponentImpl.zoomControlProvider.get(), this.cameraComponentImpl.provideZslControlProvider.get(), this.cameraComponentImpl.provideCamera2CameraControlProvider.get(), this.cameraComponentImpl.useCaseManagerProvider.get(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), this.cameraComponentImpl.videoUsageControlProvider.get());
            L9:
                return (T) new CameraGraphConfigProvider(this.cameraComponentImpl.cameraCallbackMapProvider.get(), this.cameraComponentImpl.comboRequestListenerProvider.get(), CameraConfig_ProvideCameraConfigFactory.provideCameraConfig(CameraComponentImpl.access$300(this.cameraComponentImpl)), this.cameraComponentImpl.cameraQuirksProvider.get(), this.cameraComponentImpl.provideZslControlProvider.get(), this.cameraComponentImpl.templateParamsOverride(), this.cameraComponentImpl.provideCameraMetadataProvider.get(), CameraAppConfig_ProvideCameraXConfigFactory.provideCameraXConfig(CameraAppComponentImpl.access$200(this.cameraAppComponentImpl)), CameraAppConfig_ProvideCamera2InteropCallbacksFactory.provideCamera2InteropCallbacks(CameraAppComponentImpl.access$200(this.cameraAppComponentImpl)));
            L11:
                return (T) new IntrinsicZoomCalculatorImpl(this.cameraAppComponentImpl.getCameraDevices());
            L13:
                return (T) CameraModule_Companion_ProvideCameraIdStringFactory.provideCameraIdString(CameraConfig_ProvideCameraConfigFactory.provideCameraConfig(CameraComponentImpl.access$300(this.cameraComponentImpl)));
            L15:
                return (T) CameraModule_Companion_ProvideEncoderProfilesProviderFactory.provideEncoderProfilesProvider(this.cameraComponentImpl.provideCameraIdStringProvider.get(), this.cameraComponentImpl.cameraQuirksProvider.get());
            L17:
                return (T) new CameraCallbackMap();
            L19:
                return (T) new CameraControlStateAdapter(this.cameraComponentImpl.zoomControlProvider.get(), this.cameraComponentImpl.evCompControlProvider.get(), this.cameraComponentImpl.torchControlProvider.get(), this.cameraComponentImpl.lowLightBoostControlProvider.get());
            L21:
                return (T) new CameraInfoAdapter(this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get(), CameraConfig_ProvideCameraConfigFactory.provideCameraConfig(CameraComponentImpl.access$300(this.cameraComponentImpl)), this.cameraComponentImpl.cameraStateAdapterProvider.get(), this.cameraComponentImpl.cameraControlStateAdapterProvider.get(), this.cameraComponentImpl.cameraCallbackMapProvider.get(), this.cameraComponentImpl.focusMeteringControlProvider.get(), this.cameraComponentImpl.cameraQuirksProvider.get(), this.cameraComponentImpl.provideEncoderProfilesProvider.get(), this.cameraComponentImpl.streamConfigurationMapCompatProvider.get(), this.cameraComponentImpl.intrinsicZoomCalculatorImplProvider.get(), CameraComponentImpl.access$500(this.cameraComponentImpl));
            L23:
                return (T) new CameraStateAdapter();
            L25:
                return (T) new Camera2CameraControlCompatImpl();
            L27:
                return (T) CameraModule_Companion_ProvideCamera2CameraControlFactory.provideCamera2CameraControl(this.cameraComponentImpl.camera2CameraControlCompatImplProvider.get(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), this.cameraComponentImpl.comboRequestListenerProvider.get());
            L29:
                return (T) new ZoomControl(this.cameraComponentImpl.zoomCompat());
            L31:
                return (T) new VideoUsageControl();
            L33:
                return (T) new StillCaptureRequestControl(this.cameraComponentImpl.flashControlProvider.get(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get());
            L35:
                return (T) new FocusMeteringControl(this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get(), this.cameraComponentImpl.meteringRegionCorrection(), this.cameraComponentImpl.state3AControlProvider.get(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), this.cameraComponentImpl.zoomCompat());
            L37:
                return (T) new TorchControl(this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get(), this.cameraComponentImpl.state3AControlProvider.get(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get());
            L39:
                return (T) new FlashControl(this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get(), this.cameraComponentImpl.state3AControlProvider.get(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), this.cameraComponentImpl.torchControlProvider.get(), this.cameraComponentImpl.useFlashModeTorchFor3aUpdate());
            L41:
                return (T) new EvCompImpl(this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), this.cameraComponentImpl.comboRequestListenerProvider.get());
            L43:
                return (T) new EvCompControl(this.cameraComponentImpl.evCompImplProvider.get());
            L45:
                return (T) new ComboRequestListener();
            L47:
                return (T) CameraModule_Companion_ProvideUseCaseThreadsFactory.provideUseCaseThreads(CameraConfig_ProvideCameraConfigFactory.provideCameraConfig(CameraComponentImpl.access$300(this.cameraComponentImpl)), CameraAppConfig_ProvideCameraThreadConfigFactory.provideCameraThreadConfig(CameraAppComponentImpl.access$200(this.cameraAppComponentImpl)));
            L49:
                return (T) new OutputSizesCorrector(this.cameraComponentImpl.provideCameraMetadataProvider.get(), this.cameraComponentImpl.provideStreamConfigurationMapProvider.get());
            L51:
                return (T) CameraModule.Companion.provideStreamConfigurationMap(this.cameraComponentImpl.provideCameraMetadataProvider.get());
            L53:
                return (T) new StreamConfigurationMapCompat(this.cameraComponentImpl.provideStreamConfigurationMapProvider.get(), this.cameraComponentImpl.outputSizesCorrectorProvider.get());
            L55:
                return (T) new CameraQuirks(this.cameraComponentImpl.provideCameraMetadataProvider.get(), this.cameraComponentImpl.streamConfigurationMapCompatProvider.get());
            L57:
                return (T) new State3AControl(this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get(), this.cameraComponentImpl.autoFlashAEModeDisabler(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get());
            L59:
                return (T) new LowLightBoostControl(this.cameraComponentImpl.provideCameraMetadataProvider.get(), this.cameraComponentImpl.state3AControlProvider.get(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), this.cameraComponentImpl.comboRequestListenerProvider.get());
            L61:
                return (T) CameraModule.Companion.provideCameraMetadata(CameraAppConfig_ProvideCameraPipeFactory.provideCameraPipe(CameraAppComponentImpl.access$200(this.cameraAppComponentImpl)), CameraConfig_ProvideCameraConfigFactory.provideCameraConfig(CameraComponentImpl.access$300(this.cameraComponentImpl)));
            L63:
                return (T) new CameraPipeCameraProperties(CameraConfig_ProvideCameraConfigFactory.provideCameraConfig(CameraComponentImpl.access$300(this.cameraComponentImpl)), this.cameraComponentImpl.provideCameraMetadataProvider.get());
            L65:
                return (T) CameraModule_Companion_ProvideZslControlFactory.provideZslControl(this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get());
            L69:
                return (T) new CameraInternalAdapter(CameraConfig_ProvideCameraConfigFactory.provideCameraConfig(CameraComponentImpl.access$300(this.cameraComponentImpl)), this.cameraComponentImpl.useCaseManagerProvider.get(), this.cameraComponentImpl.cameraInfoAdapterProvider.get(), this.cameraComponentImpl.cameraControlAdapterProvider.get(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), this.cameraComponentImpl.cameraStateAdapterProvider.get());
            }
        }

        public CameraComponentImpl(CameraAppComponentImpl r1, CameraConfig r2, StreamSpecsCalculator r3) {
            this.cameraComponentImpl = this;
            this.cameraAppComponentImpl = r1;
            this.cameraConfig = r2;
            this.streamSpecsCalculator = r3;
            initialize(r2, r3);
            initialize2(r2, r3);
        }

        public static /* synthetic */ CameraConfig access$300(CameraComponentImpl r0) {
            return r0.cameraConfig;
        }

        public static /* synthetic */ StreamSpecsCalculator access$500(CameraComponentImpl r0) {
            return r0.streamSpecsCalculator;
        }

        private void initialize(CameraConfig r3, StreamSpecsCalculator r4) {
            this.provideCameraMetadataProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 4));
            this.cameraPipeCameraPropertiesProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 3));
            this.provideZslControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 2));
            this.provideStreamConfigurationMapProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 9));
            this.outputSizesCorrectorProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 10));
            this.streamConfigurationMapCompatProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 8));
            this.cameraQuirksProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 7));
            this.provideUseCaseThreadsProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 11));
            this.state3AControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 6));
            this.comboRequestListenerProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 12));
            this.lowLightBoostControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 5));
            this.evCompImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 14));
            this.evCompControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 13));
            this.torchControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 16));
            this.flashControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 15));
            this.focusMeteringControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 17));
            this.stillCaptureRequestControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 18));
            this.videoUsageControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 19));
            this.zoomControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 20));
            this.camera2CameraControlCompatImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 22));
            this.provideCamera2CameraControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 21));
            this.cameraStateAdapterProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 23));
            this.cameraInternalAdapterProvider = new DelegateFactory();
            this.cameraControlStateAdapterProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 25));
            this.cameraCallbackMapProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 26));
        }

        private void initialize2(CameraConfig r3, StreamSpecsCalculator r4) {
            this.provideCameraIdStringProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 28));
            this.provideEncoderProfilesProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 27));
            this.intrinsicZoomCalculatorImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 29));
            this.cameraInfoAdapterProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 24));
            this.cameraGraphConfigProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 30));
            this.useCaseManagerProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 1));
            this.cameraControlAdapterProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 31));
            DelegateFactory.setDelegate(this.cameraInternalAdapterProvider, DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, 0)));
        }

        public AutoFlashAEModeDisabler autoFlashAEModeDisabler() {
            return AutoFlashAEModeDisabler_Bindings_Companion_ProvideAEModeDisablerFactory.provideAEModeDisabler(this.cameraQuirksProvider.get());
        }

        @Override // androidx.camera.camera2.config.CameraComponent
        public CameraInternal getCameraInternal() {
            return this.cameraInternalAdapterProvider.get();
        }

        public InactiveSurfaceCloser inactiveSurfaceCloser() {
            return InactiveSurfaceCloser_Bindings_Companion_ProvideInactiveSurfaceCloserFactory.provideInactiveSurfaceCloser(this.cameraQuirksProvider.get());
        }

        public MeteringRegionCorrection meteringRegionCorrection() {
            return MeteringRegionCorrection_Bindings_Companion_ProvideMeteringRegionCorrectionFactory.provideMeteringRegionCorrection(this.cameraQuirksProvider.get());
        }

        public Set<UseCaseCameraControl> setOfUseCaseCameraControl() {
            return setOfUseCaseCameraControlBuilder();
        }

        public Set setOfUseCaseCameraControlBuilder() {
            SetBuilder r0 = SetBuilder.newSetBuilder(9);
            r0.add(this.evCompControlProvider.get());
            r0.add(this.flashControlProvider.get());
            r0.add(this.focusMeteringControlProvider.get());
            r0.add(this.state3AControlProvider.get());
            r0.add(this.stillCaptureRequestControlProvider.get());
            r0.add(this.torchControlProvider.get());
            r0.add(this.lowLightBoostControlProvider.get());
            r0.add(this.videoUsageControlProvider.get());
            r0.add(this.zoomControlProvider.get());
            return r0.build();
        }

        public TemplateParamsOverride templateParamsOverride() {
            return TemplateParamsOverride_Bindings_Companion_ProvideTemplateParamsOverrideFactory.provideTemplateParamsOverride(this.cameraQuirksProvider.get());
        }

        public UseFlashModeTorchFor3aUpdate useFlashModeTorchFor3aUpdate() {
            return UseFlashModeTorchFor3aUpdate_Bindings_Companion_ProvideUseFlashModeTorchFor3aUpdateFactory.provideUseFlashModeTorchFor3aUpdate(this.cameraQuirksProvider.get());
        }

        public UseTorchAsFlash useTorchAsFlash() {
            return UseTorchAsFlash_Bindings_Companion_ProvideUseTorchAsFlashFactory.provideUseTorchAsFlash(this.cameraQuirksProvider.get(), this.cameraAppComponentImpl.getCameraDevices(), this.intrinsicZoomCalculatorImplProvider.get());
        }

        public ZoomCompat zoomCompat() {
            return ZoomCompat_Bindings_Companion_ProvideZoomCompatFactory.provideZoomCompat(this.cameraPipeCameraPropertiesProvider.get());
        }
    }

    public static final class UseCaseCameraComponentBuilder implements UseCaseCameraComponent.Builder {
        private final CameraAppComponentImpl cameraAppComponentImpl;
        private final CameraComponentImpl cameraComponentImpl;
        private UseCaseCameraConfig useCaseCameraConfig;

        private UseCaseCameraComponentBuilder(CameraAppComponentImpl r1, CameraComponentImpl r2) {
            this.cameraAppComponentImpl = r1;
            this.cameraComponentImpl = r2;
        }

        public /* synthetic */ UseCaseCameraComponentBuilder(CameraAppComponentImpl r1, CameraComponentImpl r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        @Override // androidx.camera.camera2.config.UseCaseCameraComponent.Builder
        public UseCaseCameraComponent build() {
            Preconditions.checkBuilderRequirement(this.useCaseCameraConfig, UseCaseCameraConfig.class);
            return new UseCaseCameraComponentImpl(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraConfig);
        }

        @Override // androidx.camera.camera2.config.UseCaseCameraComponent.Builder
        public UseCaseCameraComponentBuilder config(UseCaseCameraConfig r1) {
            this.useCaseCameraConfig = (UseCaseCameraConfig) Preconditions.checkNotNull(r1);
            return this;
        }

        @Override // androidx.camera.camera2.config.UseCaseCameraComponent.Builder
        public /* bridge */ /* synthetic */ UseCaseCameraComponent.Builder config(UseCaseCameraConfig r1) {
            return config(r1);
        }
    }

    public static final class UseCaseCameraComponentImpl implements UseCaseCameraComponent {
        private final CameraAppComponentImpl cameraAppComponentImpl;
        private final CameraComponentImpl cameraComponentImpl;
        Provider<CaptureConfigAdapter> captureConfigAdapterProvider;
        Provider<CapturePipelineImpl> capturePipelineImplProvider;
        Provider<CapturePipelineTorchCorrection> capturePipelineTorchCorrectionProvider;
        Provider<DeferredUseCaseCameraRequestControl> deferredUseCaseCameraRequestControlProvider;
        Provider<CapturePipeline> provideCapturePipelineProvider;
        Provider<SessionConfigAdapter> provideSessionConfigAdapterProvider;
        Provider<SessionProcessor> provideSessionProcessorProvider;
        Provider<UseCaseGraphContext> provideUseCaseGraphContextProvider;
        private final UseCaseCameraComponentImpl useCaseCameraComponentImpl;
        private final UseCaseCameraConfig useCaseCameraConfig;
        Provider<UseCaseCameraImpl> useCaseCameraImplProvider;
        Provider<UseCaseCameraRequestControlImpl> useCaseCameraRequestControlImplProvider;
        Provider<UseCaseCameraState> useCaseCameraStateProvider;
        Provider<UseCaseSurfaceManager> useCaseSurfaceManagerProvider;

        public static final class SwitchingProvider<T> implements Provider<T> {
            private final CameraAppComponentImpl cameraAppComponentImpl;
            private final CameraComponentImpl cameraComponentImpl;
            private final int id;
            private final UseCaseCameraComponentImpl useCaseCameraComponentImpl;

            public SwitchingProvider(CameraAppComponentImpl r1, CameraComponentImpl r2, UseCaseCameraComponentImpl r3, int r4) {
                this.cameraAppComponentImpl = r1;
                this.cameraComponentImpl = r2;
                this.useCaseCameraComponentImpl = r3;
                this.id = r4;
            }

            @Override // javax.inject.Provider
            public T get() {
                int r0 = this.id;
                switch(r0) {
                    case 0: goto L28;
                    case 1: goto L27;
                    case 2: goto L24;
                    case 3: goto L23;
                    case 4: goto L20;
                    case 5: goto L18;
                    case 6: goto L16;
                    case 7: goto L15;
                    case 8: goto L13;
                    case 9: goto L11;
                    case 10: goto L9;
                    case 11: goto L7;
                    default: goto L5;
                };
            L16:
                CaptureConfigAdapter r4 = this.useCaseCameraComponentImpl.captureConfigAdapterProvider.get();
                FlashControl r5 = this.cameraComponentImpl.flashControlProvider.get();
                TorchControl r6 = this.cameraComponentImpl.torchControlProvider.get();
                VideoUsageControl r7 = this.cameraComponentImpl.videoUsageControlProvider.get();
                UseCaseThreads r8 = this.cameraComponentImpl.provideUseCaseThreadsProvider.get();
                ComboRequestListener r9 = this.cameraComponentImpl.comboRequestListenerProvider.get();
                UseTorchAsFlash r10 = this.cameraComponentImpl.useTorchAsFlash();
                CameraPipeCameraProperties r11 = this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get();
                UseCaseCameraComponentImpl r14 = this.useCaseCameraComponentImpl;
                return (T) new CapturePipelineImpl(r4, r5, r6, r7, r8, r9, r10, r11, r14.useCaseCameraStateProvider, r14.provideUseCaseGraphContextProvider.get());
            L18:
                UseCaseCameraComponentImpl r142 = this.useCaseCameraComponentImpl;
                return (T) UseCaseCameraModule_Companion_ProvideCapturePipelineFactory.provideCapturePipeline(r142.capturePipelineImplProvider, r142.capturePipelineTorchCorrectionProvider);
            L20:
                UseCaseCameraComponentImpl r1 = this.useCaseCameraComponentImpl;
                return (T) new UseCaseCameraRequestControlImpl(r1.provideCapturePipelineProvider, r1.useCaseCameraStateProvider, r1.provideUseCaseGraphContextProvider.get(), this.useCaseCameraComponentImpl.useCaseSurfaceManagerProvider, this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), CameraAppConfig_ProvideCameraXConfigFactory.provideCameraXConfig(CameraAppComponentImpl.access$200(this.cameraAppComponentImpl)));
            L24:
                UseCaseCameraComponentImpl.access$100(this.useCaseCameraComponentImpl).provideSessionProcessor();
                return null;
            L28:
                UseCaseGraphContext r12 = this.useCaseCameraComponentImpl.provideUseCaseGraphContextProvider.get();
                UseCaseThreads r2 = this.cameraComponentImpl.provideUseCaseThreadsProvider.get();
                MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.useCaseCameraComponentImpl.provideSessionProcessorProvider.get());
                DeferredUseCaseCameraRequestControl r42 = this.useCaseCameraComponentImpl.deferredUseCaseCameraRequestControlProvider.get();
                UseCaseCameraComponentImpl r143 = this.useCaseCameraComponentImpl;
                return (T) new UseCaseCameraImpl(r12, r2, null, r42, r143.useCaseSurfaceManagerProvider, r143.provideSessionConfigAdapterProvider, r143.provideCapturePipelineProvider);
            L5:
                throw new AssertionError(r0);
            L7:
                return (T) UseCaseCameraConfig_ProvideSessionConfigAdapterFactory.provideSessionConfigAdapter(UseCaseCameraComponentImpl.access$100(this.useCaseCameraComponentImpl));
            L9:
                return (T) new UseCaseSurfaceManager(this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), CameraAppConfig_ProvideCameraPipeFactory.provideCameraPipe(CameraAppComponentImpl.access$200(this.cameraAppComponentImpl)), this.cameraComponentImpl.inactiveSurfaceCloser(), this.useCaseCameraComponentImpl.provideSessionConfigAdapterProvider.get());
            L11:
                return (T) new CapturePipelineTorchCorrection(this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get(), this.useCaseCameraComponentImpl.capturePipelineImplProvider, this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), this.cameraComponentImpl.torchControlProvider.get());
            L13:
                return (T) new UseCaseCameraState(this.useCaseCameraComponentImpl.provideUseCaseGraphContextProvider.get(), this.cameraComponentImpl.templateParamsOverride());
            L15:
                return (T) new CaptureConfigAdapter(this.cameraComponentImpl.cameraPipeCameraPropertiesProvider.get(), this.useCaseCameraComponentImpl.provideUseCaseGraphContextProvider.get(), this.cameraComponentImpl.provideZslControlProvider.get(), this.cameraComponentImpl.provideUseCaseThreadsProvider.get(), this.cameraComponentImpl.templateParamsOverride());
            L23:
                return (T) new DeferredUseCaseCameraRequestControl(this.useCaseCameraComponentImpl.useCaseCameraRequestControlImplProvider, this.cameraComponentImpl.provideUseCaseThreadsProvider.get());
            L27:
                return (T) UseCaseCameraConfig_ProvideUseCaseGraphContextFactory.provideUseCaseGraphContext(UseCaseCameraComponentImpl.access$100(this.useCaseCameraComponentImpl), this.cameraComponentImpl.cameraStateAdapterProvider.get());
            }
        }

        public UseCaseCameraComponentImpl(CameraAppComponentImpl r1, CameraComponentImpl r2, UseCaseCameraConfig r3) {
            this.useCaseCameraComponentImpl = this;
            this.cameraAppComponentImpl = r1;
            this.cameraComponentImpl = r2;
            this.useCaseCameraConfig = r3;
            initialize(r3);
        }

        public static /* synthetic */ UseCaseCameraConfig access$100(UseCaseCameraComponentImpl r0) {
            return r0.useCaseCameraConfig;
        }

        private void initialize(UseCaseCameraConfig r5) {
            this.provideUseCaseGraphContextProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 1));
            this.provideSessionProcessorProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 2));
            this.captureConfigAdapterProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 7));
            this.useCaseCameraStateProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 8));
            this.capturePipelineImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 6));
            this.capturePipelineTorchCorrectionProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 9));
            this.provideCapturePipelineProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 5));
            this.provideSessionConfigAdapterProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 11));
            this.useCaseSurfaceManagerProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 10));
            this.useCaseCameraRequestControlImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 4));
            this.deferredUseCaseCameraRequestControlProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 3));
            this.useCaseCameraImplProvider = DoubleCheck.provider(new SwitchingProvider(this.cameraAppComponentImpl, this.cameraComponentImpl, this.useCaseCameraComponentImpl, 0));
        }

        @Override // androidx.camera.camera2.config.UseCaseCameraComponent
        public UseCaseCamera getUseCaseCamera() {
            return this.useCaseCameraImplProvider.get();
        }
    }

    public static CameraAppComponent.Builder builder() {
        return new Builder(null);
    }
}
