package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.common.util.BiConsumer;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class ConfigGetParameterHandler {
    static final Pattern FALSE_REGEX = null;
    public static final Charset FRC_BYTE_ARRAY_ENCODING = null;
    static final Pattern TRUE_REGEX = null;
    private final ConfigCacheClient activatedConfigsCache;
    private final ConfigCacheClient defaultConfigsCache;
    private final Executor executor;
    private final Set<BiConsumer<String, ConfigContainer>> listeners;

    static {
        FRC_BYTE_ARRAY_ENCODING = Charset.forName("UTF-8");
        TRUE_REGEX = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
        FALSE_REGEX = Pattern.compile("^(0|false|f|no|n|off|)$", 2);
    }

    public ConfigGetParameterHandler(Executor r2, ConfigCacheClient r3, ConfigCacheClient r4) {
        this.listeners = new HashSet();
        this.executor = r2;
        this.activatedConfigsCache = r3;
        this.defaultConfigsCache = r4;
    }

    public void addListener(BiConsumer<String, ConfigContainer> r2) {
        Set<BiConsumer<String, ConfigContainer>> r0 = this.listeners;
        monitor-enter(r0);
        this.listeners.add(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
