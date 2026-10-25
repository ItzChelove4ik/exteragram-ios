package org.chromium.net.impl;

import org.chromium.net.NetworkException;

/* loaded from: classes5.dex */
class AndroidNetworkExceptionWrapper extends NetworkException {
    private final android.net.http.NetworkException mBackend;

    public AndroidNetworkExceptionWrapper(android.net.http.NetworkException r2) {
        this(r2, false);
    }

    public AndroidNetworkExceptionWrapper(android.net.http.NetworkException r2, boolean r3) {
        super(r2.getMessage(), r2);
        this.mBackend = r2;
        if (r3 == false) goto L5;
        return;
    L5:
        if (AndroidNetworkExceptionWrapper$$ExternalSyntheticApiModelOutline0.m(r2) == true) goto L8;
        return;
    L8:
        throw new IllegalArgumentException("Translating QuicException as NetworkException results in loss of information. Make sure you handle QuicException first. See the stacktrace for where the translation is being performed, and the cause for the exception being translated.", r2);
    }
}
