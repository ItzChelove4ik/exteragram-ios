package androidx.camera.core.impl;

import android.util.Pair;
import android.util.Size;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.impl.Config;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public interface ImageOutputConfig extends ReadableConfig {
    public static final Config.Option<Integer> OPTION_APP_TARGET_ROTATION = null;
    public static final Config.Option<List<Size>> OPTION_CUSTOM_ORDERED_RESOLUTIONS = null;
    public static final Config.Option<Size> OPTION_DEFAULT_RESOLUTION = null;
    public static final Config.Option<Size> OPTION_MAX_RESOLUTION = null;
    public static final Config.Option<Integer> OPTION_MIRROR_MODE = null;
    public static final Config.Option<ResolutionSelector> OPTION_RESOLUTION_SELECTOR = null;
    public static final Config.Option<List<Pair<Integer, Size[]>>> OPTION_SUPPORTED_RESOLUTIONS = null;
    public static final Config.Option<Integer> OPTION_TARGET_ASPECT_RATIO = null;
    public static final Config.Option<Size> OPTION_TARGET_RESOLUTION = null;
    public static final Config.Option<Integer> OPTION_TARGET_ROTATION = null;

    public interface Builder<B> {
        B setTargetResolution(Size r1);

        B setTargetRotation(int r1);
    }

    static {
        OPTION_TARGET_ASPECT_RATIO = Config.Option.create("camerax.core.imageOutput.targetAspectRatio", AspectRatio.class);
        Class r1 = Integer.TYPE;
        OPTION_TARGET_ROTATION = Config.Option.create("camerax.core.imageOutput.targetRotation", r1);
        OPTION_APP_TARGET_ROTATION = Config.Option.create("camerax.core.imageOutput.appTargetRotation", r1);
        OPTION_MIRROR_MODE = Config.Option.create("camerax.core.imageOutput.mirrorMode", r1);
        OPTION_TARGET_RESOLUTION = Config.Option.create("camerax.core.imageOutput.targetResolution", Size.class);
        OPTION_DEFAULT_RESOLUTION = Config.Option.create("camerax.core.imageOutput.defaultResolution", Size.class);
        OPTION_MAX_RESOLUTION = Config.Option.create("camerax.core.imageOutput.maxResolution", Size.class);
        OPTION_SUPPORTED_RESOLUTIONS = Config.Option.create("camerax.core.imageOutput.supportedResolutions", List.class);
        OPTION_RESOLUTION_SELECTOR = Config.Option.create("camerax.core.imageOutput.resolutionSelector", ResolutionSelector.class);
        OPTION_CUSTOM_ORDERED_RESOLUTIONS = Config.Option.create("camerax.core.imageOutput.customOrderedResolutions", List.class);
    }

    static void validateConfig(ImageOutputConfig r3) {
        boolean r0 = r3.hasTargetAspectRatio();
        if (r3.getTargetResolution(null) == null) goto L5;
        boolean r2 = true;
    L6:
        if (r0 == false) goto L12;
        if (r2 == false) goto L12;
        f$$ExternalSyntheticBUOutline1.m("Cannot use both setTargetResolution and setTargetAspectRatio on the same config.");
        return;
    L12:
        if (r3.getResolutionSelector(null) == null) goto L18;
        if (r0 == true) goto L16;
        if (r2 == true) goto L16;
        return;
    L16:
        f$$ExternalSyntheticBUOutline1.m("Cannot use setTargetResolution or setTargetAspectRatio with setResolutionSelector on the same config.");
        return;
    L18:
        return;
    L5:
        r2 = false;
        goto L6
    }

    default int getAppTargetRotation(int r2) {
        return ((Integer) retrieveOption(OPTION_APP_TARGET_ROTATION, Integer.valueOf(r2))).intValue();
    }

    default List<Size> getCustomOrderedResolutions(List<Size> r2) {
        List r1 = (List) retrieveOption(OPTION_CUSTOM_ORDERED_RESOLUTIONS, r2);
        if (r1 != null) goto L5;
        return null;
    L5:
        return new ArrayList(r1);
    }

    default Size getDefaultResolution(Size r2) {
        return (Size) retrieveOption(OPTION_DEFAULT_RESOLUTION, r2);
    }

    default Size getMaxResolution(Size r2) {
        return (Size) retrieveOption(OPTION_MAX_RESOLUTION, r2);
    }

    default int getMirrorMode(int r2) {
        return ((Integer) retrieveOption(OPTION_MIRROR_MODE, Integer.valueOf(r2))).intValue();
    }

    default ResolutionSelector getResolutionSelector() {
        return (ResolutionSelector) retrieveOption(OPTION_RESOLUTION_SELECTOR);
    }

    default ResolutionSelector getResolutionSelector(ResolutionSelector r2) {
        return (ResolutionSelector) retrieveOption(OPTION_RESOLUTION_SELECTOR, r2);
    }

    default List<Pair<Integer, Size[]>> getSupportedResolutions(List<Pair<Integer, Size[]>> r2) {
        return (List) retrieveOption(OPTION_SUPPORTED_RESOLUTIONS, r2);
    }

    default int getTargetAspectRatio() {
        return ((Integer) retrieveOption(OPTION_TARGET_ASPECT_RATIO)).intValue();
    }

    default Size getTargetResolution(Size r2) {
        return (Size) retrieveOption(OPTION_TARGET_RESOLUTION, r2);
    }

    default int getTargetRotation(int r2) {
        return ((Integer) retrieveOption(OPTION_TARGET_ROTATION, Integer.valueOf(r2))).intValue();
    }

    default boolean hasTargetAspectRatio() {
        return containsOption(OPTION_TARGET_ASPECT_RATIO);
    }
}
