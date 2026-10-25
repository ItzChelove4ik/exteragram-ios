package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioCapabilitiesReceiver;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAudioSink$$ExternalSyntheticLambda1 implements AudioCapabilitiesReceiver.Listener {
    public final /* synthetic */ DefaultAudioSink f$0;

    public /* synthetic */ DefaultAudioSink$$ExternalSyntheticLambda1(DefaultAudioSink r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.exoplayer.audio.AudioCapabilitiesReceiver.Listener
    public final void onAudioCapabilitiesChanged(AudioCapabilities r1) {
        this.f$0.onAudioCapabilitiesChanged(r1);
    }
}
