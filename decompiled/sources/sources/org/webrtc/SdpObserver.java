package org.webrtc;

/* loaded from: classes6.dex */
public interface SdpObserver {
    @CalledByNative
    void onCreateFailure(String r1);

    @CalledByNative
    void onCreateSuccess(SessionDescription r1);

    @CalledByNative
    void onSetFailure(String r1);

    @CalledByNative
    void onSetSuccess();
}
