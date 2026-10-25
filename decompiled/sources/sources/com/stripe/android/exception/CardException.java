package com.stripe.android.exception;

/* loaded from: classes5.dex */
public class CardException extends StripeException {
    private String charge;
    private String code;
    private String declineCode;
    private String param;

    public CardException(String r1, String r2, String r3, String r4, String r5, String r6, Integer r7, Throwable r8) {
        super(r1, r2, r7, r8);
        this.code = r3;
        this.param = r4;
        this.declineCode = r5;
        this.charge = r6;
    }
}
