package org.webrtc;

/* loaded from: classes6.dex */
public final class IceCandidateErrorEvent {
    public final String address;
    public final int errorCode;
    public final String errorText;
    public final int port;
    public final String url;

    @CalledByNative
    public IceCandidateErrorEvent(String r1, int r2, String r3, int r4, String r5) {
        this.address = r1;
        this.port = r2;
        this.url = r3;
        this.errorCode = r4;
        this.errorText = r5;
    }
}
