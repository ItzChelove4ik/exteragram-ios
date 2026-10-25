package org.chromium.net.impl;

import android.net.http.HttpException;
import org.chromium.net.CronetException;

/* loaded from: classes5.dex */
class AndroidHttpExceptionWrapper extends CronetException {
    public AndroidHttpExceptionWrapper(HttpException r2) {
        super(r2.getMessage(), r2);
    }
}
