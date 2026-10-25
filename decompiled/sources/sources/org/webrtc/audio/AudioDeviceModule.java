package org.webrtc.audio;

/* loaded from: classes6.dex */
public interface AudioDeviceModule {
    long getNativeAudioDeviceModulePointer();

    void release();

    void setMicrophoneMute(boolean r1);

    default boolean setNoiseSuppressorEnabled(boolean r1) {
        return false;
    }

    default boolean setPreferredMicrophoneFieldDimension(float r1) {
        return false;
    }

    void setSpeakerMute(boolean r1);
}
