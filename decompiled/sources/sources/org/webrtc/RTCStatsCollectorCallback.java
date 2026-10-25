package org.webrtc;

/* loaded from: classes6.dex */
public interface RTCStatsCollectorCallback {
    @CalledByNative
    void onStatsDelivered(RTCStatsReport r1);
}
