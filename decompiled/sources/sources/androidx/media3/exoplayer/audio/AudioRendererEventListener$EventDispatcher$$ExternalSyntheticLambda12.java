package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;

/* loaded from: classes4.dex */
public final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ AudioSink.AudioTrackConfig f$1;

    public /* synthetic */ AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda12(AudioRendererEventListener.EventDispatcher r1, AudioSink.AudioTrackConfig r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AudioRendererEventListener.EventDispatcher.m822$r8$lambda$PWzZVgP_qcYYwHT9ex389Si8ck(this.f$0, this.f$1);
    }
}
