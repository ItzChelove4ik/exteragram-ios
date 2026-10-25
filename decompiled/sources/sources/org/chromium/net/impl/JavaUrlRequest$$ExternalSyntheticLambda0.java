package org.chromium.net.impl;

import org.chromium.net.impl.JavaUrlRequestUtils;

/* loaded from: classes5.dex */
public final /* synthetic */ class JavaUrlRequest$$ExternalSyntheticLambda0 implements JavaUrlRequestUtils.CheckedRunnable {
    public final /* synthetic */ VersionSafeCallbacks$UploadDataProviderWrapper f$0;

    public /* synthetic */ JavaUrlRequest$$ExternalSyntheticLambda0(VersionSafeCallbacks$UploadDataProviderWrapper r1) {
        this.f$0 = r1;
    }

    @Override // org.chromium.net.impl.JavaUrlRequestUtils.CheckedRunnable
    public final void run() {
        this.f$0.close();
    }
}
