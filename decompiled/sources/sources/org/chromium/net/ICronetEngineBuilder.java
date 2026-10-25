package org.chromium.net;

/* loaded from: classes5.dex */
public abstract class ICronetEngineBuilder {
    public ICronetEngineBuilder() {
    }

    public abstract ExperimentalCronetEngine build();

    public long getLogCronetInitializationRef() {
        return 0;
    }

    public abstract ICronetEngineBuilder setExperimentalOptions(String r1);
}
