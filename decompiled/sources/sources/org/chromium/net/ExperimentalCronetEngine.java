package org.chromium.net;

import org.chromium.net.CronetEngine;

@Deprecated
/* loaded from: classes5.dex */
public abstract class ExperimentalCronetEngine extends CronetEngine {

    public static class Builder extends CronetEngine.Builder {
        public Builder(ICronetEngineBuilder r1) {
            super(r1);
        }

        @Override // org.chromium.net.CronetEngine.Builder
        public /* bridge */ /* synthetic */ CronetEngine build() {
            return build();
        }

        @Override // org.chromium.net.CronetEngine.Builder
        public ExperimentalCronetEngine build() {
            return buildExperimental();
        }
    }

    public ExperimentalCronetEngine() {
    }
}
