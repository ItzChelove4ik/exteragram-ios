package com.stripe.android.net;

/* loaded from: classes5.dex */
public class RequestOptions {
    private final String mApiVersion;
    private final String mIdempotencyKey;
    private final String mPublishableApiKey;

    /* renamed from: com.stripe.android.net.RequestOptions$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class RequestOptionsBuilder {
        private String apiVersion;
        private String idempotencyKey;
        private String publishableApiKey;

        public RequestOptionsBuilder(String r1) {
            this.publishableApiKey = r1;
        }

        public RequestOptions build() {
            return new RequestOptions(this.apiVersion, this.idempotencyKey, this.publishableApiKey, null);
        }
    }

    private RequestOptions(String r1, String r2, String r3) {
        this.mApiVersion = r1;
        this.mIdempotencyKey = r2;
        this.mPublishableApiKey = r3;
    }

    public /* synthetic */ RequestOptions(String r1, String r2, String r3, AnonymousClass1 r4) {
        this(r1, r2, r3);
    }

    public static RequestOptionsBuilder builder(String r1) {
        return new RequestOptionsBuilder(r1);
    }

    public String getApiVersion() {
        return this.mApiVersion;
    }

    public String getIdempotencyKey() {
        return this.mIdempotencyKey;
    }

    public String getPublishableApiKey() {
        return this.mPublishableApiKey;
    }
}
