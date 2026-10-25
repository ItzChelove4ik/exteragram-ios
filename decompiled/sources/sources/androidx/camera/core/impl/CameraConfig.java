package androidx.camera.core.impl;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.camera.core.impl.Config;

/* loaded from: classes3.dex */
public interface CameraConfig extends ReadableConfig {
    public static final PostviewFormatSelector DEFAULT_POSTVIEW_FORMAT_SELECTOR = null;
    public static final Config.Option<Boolean> OPTION_CAPTURE_PROCESS_PROGRESS_SUPPORTED = null;
    public static final Config.Option<Identifier> OPTION_COMPATIBILITY_ID = null;
    public static final Config.Option<PostviewFormatSelector> OPTION_POSTVIEW_FORMAT_SELECTOR = null;
    public static final Config.Option<Boolean> OPTION_POSTVIEW_SUPPORTED = null;
    public static final Config.Option<SessionProcessor> OPTION_SESSION_PROCESSOR = null;
    public static final Config.Option<UseCaseConfigFactory> OPTION_USECASE_CONFIG_FACTORY = null;
    public static final Config.Option<Integer> OPTION_USE_CASE_COMBINATION_REQUIRED_RULE = null;
    public static final Config.Option<Boolean> OPTION_ZSL_DISABLED = null;

    public interface PostviewFormatSelector {
    }

    static {
        OPTION_USECASE_CONFIG_FACTORY = Config.Option.create("camerax.core.camera.useCaseConfigFactory", UseCaseConfigFactory.class);
        OPTION_COMPATIBILITY_ID = Config.Option.create("camerax.core.camera.compatibilityId", Identifier.class);
        OPTION_USE_CASE_COMBINATION_REQUIRED_RULE = Config.Option.create("camerax.core.camera.useCaseCombinationRequiredRule", Integer.class);
        OPTION_SESSION_PROCESSOR = Config.Option.create("camerax.core.camera.SessionProcessor", SessionProcessor.class);
        OPTION_ZSL_DISABLED = Config.Option.create("camerax.core.camera.isZslDisabled", Boolean.class);
        OPTION_POSTVIEW_SUPPORTED = Config.Option.create("camerax.core.camera.isPostviewSupported", Boolean.class);
        OPTION_POSTVIEW_FORMAT_SELECTOR = Config.Option.create("camerax.core.camera.PostviewFormatSelector", PostviewFormatSelector.class);
        OPTION_CAPTURE_PROCESS_PROGRESS_SUPPORTED = Config.Option.create("camerax.core.camera.isCaptureProcessProgressSupported", Boolean.class);
        DEFAULT_POSTVIEW_FORMAT_SELECTOR = new CameraConfig$$ExternalSyntheticLambda0();
    }

    Identifier getCompatibilityId();

    default SessionProcessor getSessionProcessor(SessionProcessor r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(retrieveOption(OPTION_SESSION_PROCESSOR, r2));
        return null;
    }

    default int getUseCaseCombinationRequiredRule() {
        return ((Integer) retrieveOption(OPTION_USE_CASE_COMBINATION_REQUIRED_RULE, 0)).intValue();
    }

    default UseCaseConfigFactory getUseCaseConfigFactory() {
        return (UseCaseConfigFactory) retrieveOption(OPTION_USECASE_CONFIG_FACTORY, UseCaseConfigFactory.EMPTY_INSTANCE);
    }

    default boolean isCaptureProcessProgressSupported() {
        return ((Boolean) retrieveOption(OPTION_CAPTURE_PROCESS_PROGRESS_SUPPORTED, Boolean.FALSE)).booleanValue();
    }

    default boolean isPostviewSupported() {
        return ((Boolean) retrieveOption(OPTION_POSTVIEW_SUPPORTED, Boolean.FALSE)).booleanValue();
    }
}
