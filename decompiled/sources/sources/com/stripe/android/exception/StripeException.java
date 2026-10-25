package com.stripe.android.exception;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class StripeException extends Exception {
    private String requestId;
    private Integer statusCode;

    public StripeException(String r2, String r3, Integer r4) {
        super(r2, null);
        this.requestId = r3;
        this.statusCode = r4;
    }

    public StripeException(String r1, String r2, Integer r3, Throwable r4) {
        super(r1, r4);
        this.statusCode = r3;
        this.requestId = r2;
    }

    @Override // java.lang.Throwable
    public String toString() {
        if (this.requestId == null) goto L5;
        String r0 = "; request-id: " + this.requestId;
    L7:
        return super.toString() + r0;
    L5:
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }
}
