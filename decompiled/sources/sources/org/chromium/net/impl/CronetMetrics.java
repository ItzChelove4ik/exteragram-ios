package org.chromium.net.impl;

import internal.org.jni_zero.CalledByNative;
import internal.org.jni_zero.JNINamespace;
import org.chromium.net.RequestFinishedInfo;

@JNINamespace("cronet")
/* loaded from: classes5.dex */
public final class CronetMetrics extends RequestFinishedInfo.Metrics {
    private final long mConnectEndMs;
    private final long mConnectStartMs;
    private final long mDnsEndMs;
    private final long mDnsStartMs;
    private final long mPushEndMs;
    private final long mPushStartMs;
    private final Long mReceivedByteCount;
    private final long mRequestEndMs;
    private final long mRequestStartMs;
    private final long mResponseStartMs;
    private final long mSendingEndMs;
    private final long mSendingStartMs;
    private final Long mSentByteCount;
    private final boolean mSocketReused;
    private final long mSslEndMs;
    private final long mSslStartMs;
    private final Long mTotalTimeMs;
    private final Long mTtfbMs;

    static {
    }

    @CalledByNative
    public CronetMetrics(long r8, long r10, long r12, long r14, long r16, long r18, long r20, long r22, long r24, long r26, long r28, long r30, long r32, boolean r34, long r35, long r37) {
        this.mRequestStartMs = r8;
        this.mDnsStartMs = r10;
        this.mDnsEndMs = r12;
        this.mConnectStartMs = r14;
        this.mConnectEndMs = r16;
        this.mSslStartMs = r18;
        this.mSslEndMs = r20;
        this.mSendingStartMs = r22;
        this.mSendingEndMs = r24;
        this.mPushStartMs = r26;
        this.mPushEndMs = r28;
        this.mResponseStartMs = r30;
        this.mRequestEndMs = r32;
        this.mSocketReused = r34;
        this.mSentByteCount = Long.valueOf(r35);
        this.mReceivedByteCount = Long.valueOf(r37);
        if (r8 != (-1)) goto L5;
    L7:
        this.mTtfbMs = null;
    L8:
        if (r8 != (-1)) goto L10;
    L13:
        this.mTotalTimeMs = null;
        return;
    L10:
        if (r32 == (-1)) goto L13;
        this.mTotalTimeMs = Long.valueOf(r32 - r8);
        return;
    L5:
        if (r30 == (-1)) goto L7;
        this.mTtfbMs = Long.valueOf(r30 - r8);
        goto L8
    }

    public static CronetMetrics empty() {
        return new CronetMetrics(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, false, 0, 0);
    }
}
