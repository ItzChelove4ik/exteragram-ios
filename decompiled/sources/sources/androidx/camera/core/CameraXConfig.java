package androidx.camera.core;

import android.os.Handler;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.QuirkSettings;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.TargetConfig;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
public final class CameraXConfig implements TargetConfig<CameraX> {
    static final Config.Option<CameraSelector> OPTION_AVAILABLE_CAMERAS_LIMITER = null;
    static final Config.Option<Executor> OPTION_CAMERA_EXECUTOR = null;
    static final Config.Option<CameraFactory.Provider> OPTION_CAMERA_FACTORY_PROVIDER = null;
    static final Config.Option<Long> OPTION_CAMERA_OPEN_RETRY_MAX_TIMEOUT_IN_MILLIS_WHILE_RESUMING = null;
    static final Config.Option<RetryPolicy> OPTION_CAMERA_PROVIDER_INIT_RETRY_POLICY = null;
    static final Config.Option<CameraDeviceSurfaceManager.Provider> OPTION_DEVICE_SURFACE_MANAGER_PROVIDER = null;
    static final Config.Option<Integer> OPTION_MIN_LOGGING_LEVEL = null;
    static final Config.Option<QuirkSettings> OPTION_QUIRK_SETTINGS = null;
    static final Config.Option<Boolean> OPTION_REPEATING_STREAM_FORCED = null;
    static final Config.Option<Handler> OPTION_SCHEDULER_HANDLER = null;
    static final Config.Option<UseCaseConfigFactory.Provider> OPTION_USECASE_CONFIG_FACTORY_PROVIDER = null;
    private final OptionsBundle mConfig;

    public static final class Builder {
        private final MutableOptionsBundle mMutableConfig;

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        private Builder(MutableOptionsBundle r4) {
            this.mMutableConfig = r4;
            Class r42 = (Class) r4.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (r42 != null) goto L5;
        L9:
            setTargetClass(CameraX.class);
            return;
        L5:
            if (r42.equals(CameraX.class) == true) goto L9;
            Utils$$ExternalSyntheticBUOutline2.m("Invalid target class configuration for ", this, ": ", r42);
            throw null;
        }

        public CameraXConfig build() {
            return new CameraXConfig(OptionsBundle.from(this.mMutableConfig));
        }

        public MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        public Builder setCameraFactoryProvider(CameraFactory.Provider r3) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_CAMERA_FACTORY_PROVIDER, r3);
            return this;
        }

        public Builder setDeviceSurfaceManagerProvider(CameraDeviceSurfaceManager.Provider r3) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_DEVICE_SURFACE_MANAGER_PROVIDER, r3);
            return this;
        }

        public Builder setRepeatingStreamForced(boolean r3) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_REPEATING_STREAM_FORCED, Boolean.valueOf(r3));
            return this;
        }

        public Builder setTargetClass(Class<CameraX> r4) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_CLASS, r4);
            if (getMutableConfig().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) != null) goto L5;
            setTargetName(r4.getCanonicalName() + "-" + UUID.randomUUID());
        L5:
            return this;
        }

        public Builder setTargetName(String r3) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_NAME, r3);
            return this;
        }

        public Builder setUseCaseConfigFactoryProvider(UseCaseConfigFactory.Provider r3) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_USECASE_CONFIG_FACTORY_PROVIDER, r3);
            return this;
        }
    }

    public interface Provider {
        CameraXConfig getCameraXConfig();
    }

    static {
        OPTION_CAMERA_FACTORY_PROVIDER = Config.Option.create("camerax.core.appConfig.cameraFactoryProvider", CameraFactory.Provider.class);
        OPTION_DEVICE_SURFACE_MANAGER_PROVIDER = Config.Option.create("camerax.core.appConfig.deviceSurfaceManagerProvider", CameraDeviceSurfaceManager.Provider.class);
        OPTION_USECASE_CONFIG_FACTORY_PROVIDER = Config.Option.create("camerax.core.appConfig.useCaseConfigFactoryProvider", UseCaseConfigFactory.Provider.class);
        OPTION_CAMERA_EXECUTOR = Config.Option.create("camerax.core.appConfig.cameraExecutor", Executor.class);
        OPTION_SCHEDULER_HANDLER = Config.Option.create("camerax.core.appConfig.schedulerHandler", Handler.class);
        OPTION_MIN_LOGGING_LEVEL = Config.Option.create("camerax.core.appConfig.minimumLoggingLevel", Integer.TYPE);
        OPTION_AVAILABLE_CAMERAS_LIMITER = Config.Option.create("camerax.core.appConfig.availableCamerasLimiter", CameraSelector.class);
        OPTION_CAMERA_OPEN_RETRY_MAX_TIMEOUT_IN_MILLIS_WHILE_RESUMING = Config.Option.create("camerax.core.appConfig.cameraOpenRetryMaxTimeoutInMillisWhileResuming", Long.TYPE);
        OPTION_CAMERA_PROVIDER_INIT_RETRY_POLICY = Config.Option.create("camerax.core.appConfig.cameraProviderInitRetryPolicy", RetryPolicy.class);
        OPTION_QUIRK_SETTINGS = Config.Option.create("camerax.core.appConfig.quirksSettings", QuirkSettings.class);
        OPTION_REPEATING_STREAM_FORCED = Config.Option.create("camerax.core.appConfig.repeatingStreamForced", Boolean.TYPE);
    }

    public CameraXConfig(OptionsBundle r1) {
        this.mConfig = r1;
    }

    public CameraSelector getAvailableCamerasLimiter(CameraSelector r2) {
        return (CameraSelector) this.mConfig.retrieveOption(OPTION_AVAILABLE_CAMERAS_LIMITER, r2);
    }

    public Executor getCameraExecutor(Executor r2) {
        return (Executor) this.mConfig.retrieveOption(OPTION_CAMERA_EXECUTOR, r2);
    }

    public CameraFactory.Provider getCameraFactoryProvider(CameraFactory.Provider r2) {
        return (CameraFactory.Provider) this.mConfig.retrieveOption(OPTION_CAMERA_FACTORY_PROVIDER, r2);
    }

    public long getCameraOpenRetryMaxTimeoutInMillisWhileResuming() {
        return ((Long) this.mConfig.retrieveOption(OPTION_CAMERA_OPEN_RETRY_MAX_TIMEOUT_IN_MILLIS_WHILE_RESUMING, -1L)).longValue();
    }

    public RetryPolicy getCameraProviderInitRetryPolicy() {
        RetryPolicy r2 = (RetryPolicy) this.mConfig.retrieveOption(OPTION_CAMERA_PROVIDER_INIT_RETRY_POLICY, RetryPolicy.DEFAULT);
        Objects.requireNonNull(r2);
        return r2;
    }

    @Override // androidx.camera.core.impl.ReadableConfig
    public Config getConfig() {
        return this.mConfig;
    }

    public CameraDeviceSurfaceManager.Provider getDeviceSurfaceManagerProvider(CameraDeviceSurfaceManager.Provider r2) {
        return (CameraDeviceSurfaceManager.Provider) this.mConfig.retrieveOption(OPTION_DEVICE_SURFACE_MANAGER_PROVIDER, r2);
    }

    public QuirkSettings getQuirkSettings() {
        return (QuirkSettings) this.mConfig.retrieveOption(OPTION_QUIRK_SETTINGS, null);
    }

    public Handler getSchedulerHandler(Handler r2) {
        return (Handler) this.mConfig.retrieveOption(OPTION_SCHEDULER_HANDLER, r2);
    }

    public UseCaseConfigFactory.Provider getUseCaseConfigFactoryProvider(UseCaseConfigFactory.Provider r2) {
        return (UseCaseConfigFactory.Provider) this.mConfig.retrieveOption(OPTION_USECASE_CONFIG_FACTORY_PROVIDER, r2);
    }

    public boolean isRepeatingStreamForced() {
        return ((Boolean) this.mConfig.retrieveOption(OPTION_REPEATING_STREAM_FORCED, Boolean.TRUE)).booleanValue();
    }
}
