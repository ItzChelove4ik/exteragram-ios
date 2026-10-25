package androidx.camera.core.impl;

import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.Config;
import androidx.core.util.Preconditions;

/* loaded from: classes3.dex */
public interface ImageInputConfig extends ReadableConfig {
    public static final Config.Option<DynamicRange> OPTION_INPUT_DYNAMIC_RANGE = null;
    public static final Config.Option<Integer> OPTION_INPUT_FORMAT = null;
    public static final Config.Option<Integer> OPTION_SECONDARY_INPUT_FORMAT = null;

    static {
        Class r1 = Integer.TYPE;
        OPTION_INPUT_FORMAT = Config.Option.create("camerax.core.imageInput.inputFormat", r1);
        OPTION_SECONDARY_INPUT_FORMAT = Config.Option.create("camerax.core.imageInput.secondaryInputFormat", r1);
        OPTION_INPUT_DYNAMIC_RANGE = Config.Option.create("camerax.core.imageInput.inputDynamicRange", DynamicRange.class);
    }

    default DynamicRange getDynamicRange() {
        return (DynamicRange) Preconditions.checkNotNull((DynamicRange) retrieveOption(OPTION_INPUT_DYNAMIC_RANGE, DynamicRange.UNSPECIFIED));
    }

    default int getInputFormat() {
        return ((Integer) retrieveOption(OPTION_INPUT_FORMAT)).intValue();
    }

    default int getSecondaryInputFormat() {
        return ((Integer) retrieveOption(OPTION_SECONDARY_INPUT_FORMAT, 0)).intValue();
    }

    default boolean hasDynamicRange() {
        return containsOption(OPTION_INPUT_DYNAMIC_RANGE);
    }
}
