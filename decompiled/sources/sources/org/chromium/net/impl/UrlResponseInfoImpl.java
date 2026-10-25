package org.chromium.net.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import org.chromium.net.UrlResponseInfo;

/* loaded from: classes5.dex */
public final class UrlResponseInfoImpl extends UrlResponseInfo {
    private final HeaderBlockImpl mHeaders;
    private final int mHttpStatusCode;
    private final String mHttpStatusText;
    private final String mNegotiatedProtocol;
    private final String mProxyServer;
    private final AtomicLong mReceivedByteCount;
    private final List<String> mResponseInfoUrlChain;
    private final boolean mWasCached;

    public static final class HeaderBlockImpl extends UrlResponseInfo.HeaderBlock {
        private final List<Map.Entry<String, String>> mAllHeadersList;
        private Map<String, List<String>> mHeadersMap;

        public HeaderBlockImpl(List<Map.Entry<String, String>> r1) {
            this.mAllHeadersList = r1;
        }

        public List<Map.Entry<String, String>> getAsList() {
            return this.mAllHeadersList;
        }

        public Map<String, List<String>> getAsMap() {
            Map<String, List<String>> r0 = this.mHeadersMap;
            if (r0 == null) goto L5;
            return r0;
        L5:
            TreeMap r02 = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            Iterator<Map.Entry<String, String>> r1 = this.mAllHeadersList.iterator();
        L7:
            if (r1.hasNext() == false) goto L12;
            Map.Entry<String, String> r2 = r1.next();
            ArrayList r3 = new ArrayList();
            if (r02.containsKey(r2.getKey()) == false) goto L11;
            r3.addAll((Collection) r02.get(r2.getKey()));
        L11:
            r3.add(r2.getValue());
            r02.put(r2.getKey(), Collections.unmodifiableList(r3));
            goto L7
        L12:
            Map<String, List<String>> r03 = Collections.unmodifiableMap(r02);
            this.mHeadersMap = r03;
            return r03;
        }
    }

    public UrlResponseInfoImpl(List<String> r1, int r2, String r3, List<Map.Entry<String, String>> r4, boolean r5, String r6, String r7, long r8) {
        this.mResponseInfoUrlChain = Collections.unmodifiableList(r1);
        this.mHttpStatusCode = r2;
        this.mHttpStatusText = r3;
        this.mHeaders = new HeaderBlockImpl(Collections.unmodifiableList(r4));
        this.mWasCached = r5;
        this.mNegotiatedProtocol = r6;
        this.mProxyServer = r7;
        this.mReceivedByteCount = new AtomicLong(r8);
    }

    public Map<String, List<String>> getAllHeaders() {
        return this.mHeaders.getAsMap();
    }

    public List<Map.Entry<String, String>> getAllHeadersAsList() {
        return this.mHeaders.getAsList();
    }

    @Override // org.chromium.net.UrlResponseInfo
    public int getHttpStatusCode() {
        return this.mHttpStatusCode;
    }

    public String getHttpStatusText() {
        return this.mHttpStatusText;
    }

    public String getNegotiatedProtocol() {
        return this.mNegotiatedProtocol;
    }

    public String getProxyServer() {
        return this.mProxyServer;
    }

    public long getReceivedByteCount() {
        return this.mReceivedByteCount.get();
    }

    @Override // org.chromium.net.UrlResponseInfo
    public String getUrl() {
        return this.mResponseInfoUrlChain.get(r1.size() - 1);
    }

    public List<String> getUrlChain() {
        return this.mResponseInfoUrlChain;
    }

    public String toString() {
        return String.format(Locale.ROOT, "UrlResponseInfo@[%s][%s]: urlChain = %s, httpStatus = %d %s, headers = %s, wasCached = %b, negotiatedProtocol = %s, proxyServer= %s, receivedByteCount = %d", new Object[]{Integer.toHexString(System.identityHashCode(this)), getUrl(), getUrlChain().toString(), Integer.valueOf(getHttpStatusCode()), getHttpStatusText(), getAllHeadersAsList().toString(), Boolean.valueOf(wasCached()), getNegotiatedProtocol(), getProxyServer(), Long.valueOf(getReceivedByteCount())});
    }

    public boolean wasCached() {
        return this.mWasCached;
    }
}
