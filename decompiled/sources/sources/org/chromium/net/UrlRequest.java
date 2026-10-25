package org.chromium.net;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public abstract class UrlRequest {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract Builder addHeader(String r1, String r2);

        public abstract UrlRequest build();

        public abstract Builder setHttpMethod(String r1);

        public abstract Builder setTrafficStatsTag(int r1);

        public abstract Builder setUploadDataProvider(UploadDataProvider r1, Executor r2);
    }

    public static abstract class Callback {
        public Callback() {
        }

        public void onCanceled(UrlRequest r1, UrlResponseInfo r2) {
        }

        public abstract void onFailed(UrlRequest r1, UrlResponseInfo r2, CronetException r3);

        public abstract void onReadCompleted(UrlRequest r1, UrlResponseInfo r2, ByteBuffer r3);

        public abstract void onRedirectReceived(UrlRequest r1, UrlResponseInfo r2, String r3);

        public abstract void onResponseStarted(UrlRequest r1, UrlResponseInfo r2);

        public abstract void onSucceeded(UrlRequest r1, UrlResponseInfo r2);
    }

    public UrlRequest() {
    }

    public abstract void cancel();

    public abstract void followRedirect();

    public abstract void read(ByteBuffer r1);

    public abstract void start();
}
