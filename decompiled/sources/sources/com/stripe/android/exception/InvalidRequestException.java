package com.stripe.android.exception;

/* loaded from: classes5.dex */
public class InvalidRequestException extends StripeException {
    private final String param;

    public InvalidRequestException(String r1, String r2, String r3, Integer r4, Throwable r5) {
        super(r1, r3, r4, r5);
        this.param = r2;
    }
}
