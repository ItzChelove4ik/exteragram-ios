package org.chromium.net.impl;

import org.chromium.net.UrlResponseInfo;

/* loaded from: classes5.dex */
class AndroidUrlResponseInfoWrapper extends UrlResponseInfo {
    private final android.net.http.UrlResponseInfo mBackend;
    private final String mProxyServerCompat;

    private AndroidUrlResponseInfoWrapper(android.net.http.UrlResponseInfo r1, String r2) {
        this.mBackend = r1;
        this.mProxyServerCompat = r2;
    }

    public static AndroidUrlResponseInfoWrapper createForUrlRequest(android.net.http.UrlResponseInfo r2) {
        if (isResponseInfoNull(r2) == false) goto L7;
        return null;
    L7:
        return new AndroidUrlResponseInfoWrapper(r2, ":0");
    }

    private static boolean isResponseInfoNull(android.net.http.UrlResponseInfo r1) {
        if (r1 != null) goto L9;
        return true;
    L9:
        r1.getUrl();     // Catch: NullPointerException -> L8
        return false;
    L8:
        return true;
    }

    @Override // org.chromium.net.UrlResponseInfo
    public int getHttpStatusCode() {
        return this.mBackend.getHttpStatusCode();
    }

    @Override // org.chromium.net.UrlResponseInfo
    public String getUrl() {
        return this.mBackend.getUrl();
    }
}
