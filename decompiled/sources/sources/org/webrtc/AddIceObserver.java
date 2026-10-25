package org.webrtc;

/* loaded from: classes6.dex */
public interface AddIceObserver {
    @CalledByNative
    void onAddFailure(String r1);

    @CalledByNative
    void onAddSuccess();
}
