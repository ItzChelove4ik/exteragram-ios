package org.chromium.net.impl;

import android.content.Context;
import java.util.concurrent.atomic.AtomicLong;
import org.chromium.net.ExperimentalCronetEngine;
import org.chromium.net.ICronetEngineBuilder;
import org.chromium.net.impl.CronetLogger;

/* loaded from: classes5.dex */
public class JavaCronetEngineBuilderImpl extends CronetEngineBuilderImpl {
    private static final AtomicLong sLogCronetInitializationRef = null;

    static {
        sLogCronetInitializationRef = new AtomicLong(0);
    }

    public JavaCronetEngineBuilderImpl(Context r2) {
        super(r2, CronetLogger.CronetSource.CRONET_SOURCE_FALLBACK);
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public ExperimentalCronetEngine build() {
        if (getUserAgent() != null) goto L6;
        setUserAgent(getDefaultUserAgent());
    L6:
        return new JavaCronetEngine(this);
    }

    @Override // org.chromium.net.impl.CronetEngineBuilderImpl, org.chromium.net.ICronetEngineBuilder
    public long getLogCronetInitializationRef() {
        AtomicLong r0 = sLogCronetInitializationRef;
        r0.compareAndSet(0, this.mLogger.generateId());
        return r0.get();
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public /* bridge */ /* synthetic */ ICronetEngineBuilder setExperimentalOptions(String r1) {
        return super.setExperimentalOptions(r1);
    }
}
