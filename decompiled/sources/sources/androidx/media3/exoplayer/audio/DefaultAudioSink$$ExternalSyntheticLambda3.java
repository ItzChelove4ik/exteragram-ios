package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioSink;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAudioSink$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ AudioSink.Listener f$0;
    public final /* synthetic */ AudioSink.AudioTrackConfig f$1;

    public /* synthetic */ DefaultAudioSink$$ExternalSyntheticLambda3(AudioSink.Listener r1, AudioSink.AudioTrackConfig r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DefaultAudioSink.$r8$lambda$HSqvmOmbsjCp8OxUgX7XYweSoS0(this.f$0, this.f$1);
    }
}
