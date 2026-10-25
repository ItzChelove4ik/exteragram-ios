package org.chromium.net.impl;

import android.net.Network;
import android.net.http.HttpEngine;
import android.net.http.UrlRequest;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.RequestFinishedInfo;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UrlRequest;

/* loaded from: classes5.dex */
class AndroidHttpEngineWrapper extends CronetEngineBase {
    private final HttpEngine mBackend;
    private final Map<RequestFinishedInfo.Listener, VersionSafeCallbacks$RequestFinishedInfoListener> mFinishedListenerMap;

    /* renamed from: $r8$lambda$hyJ0rO7V-u1T5-Vvo3JjtbhYpM4, reason: not valid java name */
    public static /* synthetic */ void m3803$r8$lambda$hyJ0rO7Vu1T5Vvo3JjtbhYpM4(VersionSafeCallbacks$RequestFinishedInfoListener r1, RequestFinishedInfo r2) {
        r1.onRequestFinished(r2);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        Log.e("HttpEngineWrapper", "Exception thrown from observation task", e);
    }

    public AndroidHttpEngineWrapper(HttpEngine r2) {
        this.mFinishedListenerMap = Collections.synchronizedMap(new HashMap());
        this.mBackend = r2;
    }

    private Network getNetwork(long r3) {
        if (r3 != (-1)) goto L7;
        return null;
    L7:
        return Network.fromNetworkHandle(r3);
    }

    @Override // org.chromium.net.impl.CronetEngineBase
    public ExperimentalUrlRequest createRequest(String r5, UrlRequest.Callback r6, Executor r7, int r8, Collection<Object> r9, boolean r10, boolean r11, boolean r12, boolean r13, int r14, boolean r15, int r16, RequestFinishedInfo.Listener r17, int r18, long r19, String r21, ArrayList<Map.Entry<String, String>> r22, UploadDataProvider r23, Executor r24, byte[] r25, ByteBuffer r26, String r27) {
        AndroidUrlRequestCallbackWrapper r2 = new AndroidUrlRequestCallbackWrapper(r6);
        UrlRequest.Builder r62 = this.mBackend.newUrlRequestBuilder(r5, r7, r2);
        r62.setPriority(r8);
        if (r10 == false) goto L5;
        r62.setCacheDisabled(r10);
    L5:
        if (r12 == false) goto L7;
        r62.setDirectExecutorAllowed(r12);
    L7:
        if (r13 == false) goto L9;
        r62.setTrafficStatsTag(r14);
    L9:
        if (r15 == false) goto L11;
        r62.setTrafficStatsTag(r16);
    L11:
        r62.bindToNetwork(getNetwork(r19));
        r62.setHttpMethod(r21);
        int r72 = r22.size();
        int r82 = 0;
    L12:
        if (r82 >= r72) goto L14;
        Map.Entry<String, String> r0 = r22.get(r82);
        r82 = r82 + 1;
        Map.Entry<String, String> r02 = r0;
        r62.addHeader(r02.getKey(), r02.getValue());
        goto L12
    L14:
        if (r23 == null) goto L17;
        r62.setUploadDataProvider(new AndroidUploadDataProviderWrapper(r23), r24);
    L17:
        return AndroidUrlRequestWrapper.createAndAddToCallback(r62.build(), r2, this, r5, r9, r17);
    }

    @Override // org.chromium.net.CronetEngine
    public /* bridge */ /* synthetic */ UrlRequest.Builder newUrlRequestBuilder(String r1, UrlRequest.Callback r2, Executor r3) {
        return super.newUrlRequestBuilder(r1, r2, r3);
    }

    public void reportRequestFinished(RequestFinishedInfo r5, VersionSafeCallbacks$RequestFinishedInfoListener r6) {
        ArrayList r0 = new ArrayList();
        Map<RequestFinishedInfo.Listener, VersionSafeCallbacks$RequestFinishedInfoListener> r1 = this.mFinishedListenerMap;
        monitor-enter(r1);
        r0.addAll(this.mFinishedListenerMap.values());     // Catch: Throwable -> L16
        monitor-exit(r1);     // Catch: Throwable -> L16
        if (r6 == null) goto L8;
        r0.add(r6);
    L8:
        int r4 = r0.size();
        int r62 = 0;
    L9:
        if (r62 >= r4) goto L15;
        Object r12 = r0.get(r62);
        r62 = r62 + 1;
        VersionSafeCallbacks$RequestFinishedInfoListener r13 = (VersionSafeCallbacks$RequestFinishedInfoListener) r12;
        r13.getExecutor().execute(new AndroidHttpEngineWrapper$$ExternalSyntheticLambda0(r13, r5));     // Catch: RejectedExecutionException -> L13
    L13:
        e = move-exception;
        Log.e("HttpEngineWrapper", "Exception posting task to executor", e);
        goto L9
    L15:
        return;
    L16:
        th = move-exception;
        throw th;
    }
}
