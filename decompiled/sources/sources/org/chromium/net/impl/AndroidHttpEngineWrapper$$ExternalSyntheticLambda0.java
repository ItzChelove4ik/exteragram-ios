package org.chromium.net.impl;

import org.chromium.net.RequestFinishedInfo;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidHttpEngineWrapper$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ VersionSafeCallbacks$RequestFinishedInfoListener f$0;
    public final /* synthetic */ RequestFinishedInfo f$1;

    public /* synthetic */ AndroidHttpEngineWrapper$$ExternalSyntheticLambda0(VersionSafeCallbacks$RequestFinishedInfoListener r1, RequestFinishedInfo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AndroidHttpEngineWrapper.m3803$r8$lambda$hyJ0rO7Vu1T5Vvo3JjtbhYpM4(this.f$0, this.f$1);
    }
}
