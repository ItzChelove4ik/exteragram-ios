package org.webrtc.audio;

@Deprecated
/* loaded from: classes6.dex */
public class LegacyAudioDeviceModule implements AudioDeviceModule {
    public LegacyAudioDeviceModule() {
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public long getNativeAudioDeviceModulePointer() {
        return 0;
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void release() {
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void setMicrophoneMute(boolean r1) {
        org.webrtc.voiceengine.WebRtcAudioRecord.setMicrophoneMute(r1);
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void setSpeakerMute(boolean r1) {
        org.webrtc.voiceengine.WebRtcAudioTrack.setSpeakerMute(r1);
    }
}
