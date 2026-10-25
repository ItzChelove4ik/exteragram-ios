package org.webrtc;

/* loaded from: classes6.dex */
public class AudioTrack extends MediaStreamTrack {
    public AudioTrack(long r1) {
        super(r1);
    }

    private static native void nativeSetVolume(long r0, double r2);

    public long getNativeAudioTrack() {
        return getNativeMediaStreamTrack();
    }

    public void setVolume(double r3) {
        nativeSetVolume(getNativeAudioTrack(), r3);
    }
}
