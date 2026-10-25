package org.chromium.net.impl;

import org.chromium.net.impl.CronetLogger;

/* loaded from: classes5.dex */
public final class NoOpLogger extends CronetLogger {
    public NoOpLogger() {
    }

    @Override // org.chromium.net.impl.CronetLogger
    public long generateId() {
        return 0;
    }

    @Override // org.chromium.net.impl.CronetLogger
    public void logCronetEngineBuilderInitializedInfo(CronetLogger.CronetEngineBuilderInitializedInfo r1) {
    }

    @Override // org.chromium.net.impl.CronetLogger
    public void logCronetEngineCreation(long r1, CronetLogger.CronetEngineBuilderInfo r3, CronetLogger.CronetVersion r4, CronetLogger.CronetSource r5) {
    }

    @Override // org.chromium.net.impl.CronetLogger
    public void logCronetTrafficInfo(long r1, CronetLogger.CronetTrafficInfo r3) {
    }
}
