package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;

/* loaded from: classes3.dex */
public interface MutableConfig extends Config {
    <ValueT> void insertOption(Config.Option<ValueT> r1, Config.OptionPriority r2, ValueT r3);

    <ValueT> void insertOption(Config.Option<ValueT> r1, ValueT r2);
}
