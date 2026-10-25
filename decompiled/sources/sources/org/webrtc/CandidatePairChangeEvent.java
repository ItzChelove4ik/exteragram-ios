package org.webrtc;

/* loaded from: classes6.dex */
public final class CandidatePairChangeEvent {
    public final int estimatedDisconnectedTimeMs;
    public final int lastDataReceivedMs;
    public final IceCandidate local;
    public final String reason;
    public final IceCandidate remote;

    @CalledByNative
    public CandidatePairChangeEvent(IceCandidate r1, IceCandidate r2, int r3, String r4, int r5) {
        this.local = r1;
        this.remote = r2;
        this.lastDataReceivedMs = r3;
        this.reason = r4;
        this.estimatedDisconnectedTimeMs = r5;
    }
}
