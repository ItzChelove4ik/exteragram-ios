package androidx.camera.core.internal;

import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ReadableConfig;

/* loaded from: classes3.dex */
public interface TargetConfig<T> extends ReadableConfig {
    public static final Config.Option<Class<?>> OPTION_TARGET_CLASS = null;
    public static final Config.Option<String> OPTION_TARGET_NAME = null;

    static {
        OPTION_TARGET_NAME = Config.Option.create("camerax.core.target.name", String.class);
        OPTION_TARGET_CLASS = Config.Option.create("camerax.core.target.class", Class.class);
    }

    default String getTargetName() {
        return (String) retrieveOption(OPTION_TARGET_NAME);
    }

    default String getTargetName(String r2) {
        return (String) retrieveOption(OPTION_TARGET_NAME, r2);
    }
}
