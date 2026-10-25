package org.chromium.net.impl;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Executor;
import org.chromium.net.ExperimentalCronetEngine;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UrlRequest;

/* loaded from: classes5.dex */
public abstract class CronetEngineBase extends ExperimentalCronetEngine {
    public CronetEngineBase() {
    }

    public abstract ExperimentalUrlRequest createRequest(String r1, UrlRequest.Callback r2, Executor r3, int r4, Collection<Object> r5, boolean r6, boolean r7, boolean r8, boolean r9, int r10, boolean r11, int r12, RequestFinishedInfo.Listener r13, int r14, long r15, String r17, ArrayList<Map.Entry<String, String>> r18, UploadDataProvider r19, Executor r20, byte[] r21, ByteBuffer r22, String r23);

    @Override // org.chromium.net.CronetEngine
    public ExperimentalUrlRequest.Builder newUrlRequestBuilder(String r2, UrlRequest.Callback r3, Executor r4) {
        return new UrlRequestBuilderImpl(r2, r3, r4, this);
    }
}
