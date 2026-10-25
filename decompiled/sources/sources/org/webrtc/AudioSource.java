package org.webrtc;

/* loaded from: classes6.dex */
public class AudioSource extends MediaSource {
    public AudioSource(long r1) {
        super(r1);
    }

    public long getNativeAudioSource() {
        return getNativeMediaSource();
    }
}
