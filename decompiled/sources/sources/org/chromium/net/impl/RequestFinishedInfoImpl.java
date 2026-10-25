package org.chromium.net.impl;

import java.util.Collection;
import org.chromium.net.CronetException;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UrlResponseInfo;

/* loaded from: classes5.dex */
public abstract class RequestFinishedInfoImpl extends RequestFinishedInfo {
    private final Collection<Object> mAnnotations;
    private final CronetException mException;
    private final int mFinishedReason;
    private final RequestFinishedInfo.Metrics mMetrics;
    private final UrlResponseInfo mResponseInfo;
    private final String mUrl;

    public RequestFinishedInfoImpl(String r1, Collection<Object> r2, RequestFinishedInfo.Metrics r3, int r4, UrlResponseInfo r5, CronetException r6) {
        this.mUrl = r1;
        this.mAnnotations = r2;
        this.mMetrics = r3;
        this.mFinishedReason = r4;
        this.mResponseInfo = r5;
        this.mException = r6;
    }
}
