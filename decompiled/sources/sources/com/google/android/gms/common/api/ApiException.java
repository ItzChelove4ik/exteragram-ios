package com.google.android.gms.common.api;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class ApiException extends Exception {

    @Deprecated
    protected final Status mStatus;

    public ApiException(Status r6) {
        int r0 = r6.getStatusCode();
        if (r6.getStatusMessage() == null) goto L5;
        String r1 = r6.getStatusMessage();
    L6:
        int r2 = String.valueOf(r0).length();
        StringBuilder r4 = new StringBuilder((r2 + 2) + String.valueOf(r1).length());
        r4.append(r0);
        r4.append(": ");
        r4.append(r1);
        super(r4.toString());
        this.mStatus = r6;
        return;
    L5:
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public int getStatusCode() {
        return this.mStatus.getStatusCode();
    }
}
