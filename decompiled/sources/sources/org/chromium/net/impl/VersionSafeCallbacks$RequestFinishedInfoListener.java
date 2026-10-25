package org.chromium.net.impl;

import java.util.concurrent.Executor;
import org.chromium.net.RequestFinishedInfo;

/* loaded from: classes5.dex */
public final class VersionSafeCallbacks$RequestFinishedInfoListener extends RequestFinishedInfo.Listener {
    private final RequestFinishedInfo.Listener mWrappedListener;

    public VersionSafeCallbacks$RequestFinishedInfoListener(RequestFinishedInfo.Listener r2) {
        super(r2.getExecutor());
        this.mWrappedListener = r2;
    }

    @Override // org.chromium.net.RequestFinishedInfo.Listener
    public Executor getExecutor() {
        return this.mWrappedListener.getExecutor();
    }

    @Override // org.chromium.net.RequestFinishedInfo.Listener
    public void onRequestFinished(RequestFinishedInfo r1) {
        this.mWrappedListener.onRequestFinished(r1);
    }
}
