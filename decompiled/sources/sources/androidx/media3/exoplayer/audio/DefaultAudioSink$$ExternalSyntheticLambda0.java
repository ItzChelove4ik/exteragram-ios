package androidx.media3.exoplayer.audio;

import android.media.AudioTrack;
import android.os.Handler;
import androidx.media3.exoplayer.audio.AudioSink;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAudioSink$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AudioTrack f$0;
    public final /* synthetic */ AudioSink.Listener f$1;
    public final /* synthetic */ Handler f$2;
    public final /* synthetic */ AudioSink.AudioTrackConfig f$3;

    public /* synthetic */ DefaultAudioSink$$ExternalSyntheticLambda0(AudioTrack r1, AudioSink.Listener r2, Handler r3, AudioSink.AudioTrackConfig r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DefaultAudioSink.m825$r8$lambda$idAuhXPZKkc9ZJ9aYo_jnodQOk(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
