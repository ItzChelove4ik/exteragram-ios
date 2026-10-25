package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;
import java.util.Set;

/* loaded from: classes3.dex */
public interface ReadableConfig extends Config {
    @Override // androidx.camera.core.impl.Config
    default boolean containsOption(Config.Option<?> r1) {
        return getConfig().containsOption(r1);
    }

    @Override // androidx.camera.core.impl.Config
    default void findOptions(String r1, Config.OptionMatcher r2) {
        getConfig().findOptions(r1, r2);
    }

    Config getConfig();

    @Override // androidx.camera.core.impl.Config
    default Config.OptionPriority getOptionPriority(Config.Option<?> r1) {
        return getConfig().getOptionPriority(r1);
    }

    @Override // androidx.camera.core.impl.Config
    default Set<Config.OptionPriority> getPriorities(Config.Option<?> r1) {
        return getConfig().getPriorities(r1);
    }

    @Override // androidx.camera.core.impl.Config
    default Set<Config.Option<?>> listOptions() {
        return getConfig().listOptions();
    }

    @Override // androidx.camera.core.impl.Config
    default <ValueT> ValueT retrieveOption(Config.Option<ValueT> r1) {
        return (ValueT) getConfig().retrieveOption(r1);
    }

    @Override // androidx.camera.core.impl.Config
    default <ValueT> ValueT retrieveOption(Config.Option<ValueT> r1, ValueT r2) {
        return (ValueT) getConfig().retrieveOption(r1, r2);
    }

    @Override // androidx.camera.core.impl.Config
    default <ValueT> ValueT retrieveOptionWithPriority(Config.Option<ValueT> r1, Config.OptionPriority r2) {
        return (ValueT) getConfig().retrieveOptionWithPriority(r1, r2);
    }
}
