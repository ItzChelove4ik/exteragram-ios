package org.chromium.net.impl;

import org.chromium.net.QuicException;

/* loaded from: classes5.dex */
class AndroidQuicExceptionWrapper extends QuicException {
    private final AndroidNetworkExceptionWrapper mBackend;

    public AndroidQuicExceptionWrapper(android.net.http.QuicException r3) {
        super(r3.getMessage(), r3);
        this.mBackend = new AndroidNetworkExceptionWrapper(r3, true);
    }
}
