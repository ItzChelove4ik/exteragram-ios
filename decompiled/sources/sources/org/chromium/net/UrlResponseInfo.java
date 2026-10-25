package org.chromium.net;

/* loaded from: classes5.dex */
public abstract class UrlResponseInfo {

    public static abstract class HeaderBlock {
        public HeaderBlock() {
        }
    }

    public UrlResponseInfo() {
    }

    public abstract int getHttpStatusCode();

    public abstract String getUrl();
}
