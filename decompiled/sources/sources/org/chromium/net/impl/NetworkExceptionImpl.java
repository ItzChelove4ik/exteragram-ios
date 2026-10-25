package org.chromium.net.impl;

import org.chromium.net.NetworkException;

/* loaded from: classes5.dex */
public class NetworkExceptionImpl extends NetworkException {
    protected final int mCronetInternalErrorCode;
    protected final int mErrorCode;

    static {
    }

    public NetworkExceptionImpl(String r2, int r3, int r4) {
        super(r2, null);
        this.mErrorCode = r3;
        this.mCronetInternalErrorCode = r4;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder r0 = new StringBuilder(super.getMessage());
        r0.append(", ErrorCode=");
        r0.append(this.mErrorCode);
        if (this.mCronetInternalErrorCode == 0) goto L5;
        r0.append(", InternalErrorCode=");
        r0.append(this.mCronetInternalErrorCode);
    L5:
        r0.append(", Retryable=");
        r0.append(immediatelyRetryable());
        return r0.toString();
    }

    public boolean immediatelyRetryable() {
        int r1 = this.mErrorCode;
        if (r1 != 3) goto L5;
        return true;
    L5:
        if (r1 != 4) goto L7;
        return true;
    L7:
        if (r1 != 5) goto L9;
        return true;
    L9:
        if (r1 != 6) goto L11;
        return true;
    L11:
        if (r1 == 8) goto L19;
        return false;
    L19:
        return true;
    }
}
