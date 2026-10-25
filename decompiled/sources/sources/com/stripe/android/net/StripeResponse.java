package com.stripe.android.net;

import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class StripeResponse {
    private String mResponseBody;
    private int mResponseCode;
    private Map<String, List<String>> mResponseHeaders;

    public StripeResponse(int r1, String r2, Map<String, List<String>> r3) {
        this.mResponseCode = r1;
        this.mResponseBody = r2;
        this.mResponseHeaders = r3;
    }

    public String getResponseBody() {
        return this.mResponseBody;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.mResponseHeaders;
    }
}
