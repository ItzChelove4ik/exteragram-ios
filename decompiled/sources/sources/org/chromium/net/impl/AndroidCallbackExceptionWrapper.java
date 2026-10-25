package org.chromium.net.impl;

import org.chromium.net.CallbackException;

/* loaded from: classes5.dex */
class AndroidCallbackExceptionWrapper extends CallbackException {
    public AndroidCallbackExceptionWrapper(android.net.http.CallbackException r2) {
        super(r2.getMessage(), r2.getCause());
    }
}
