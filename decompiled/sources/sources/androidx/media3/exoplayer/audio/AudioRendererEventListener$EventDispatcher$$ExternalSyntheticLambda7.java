package androidx.media3.exoplayer.audio;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f$0;
    public final /* synthetic */ Format f$1;
    public final /* synthetic */ DecoderReuseEvaluation f$2;

    public /* synthetic */ AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7(AudioRendererEventListener.EventDispatcher r1, Format r2, DecoderReuseEvaluation r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AudioRendererEventListener.EventDispatcher.$r8$lambda$Z5f042bJXV5vEZXCrKisqN6sD6A(this.f$0, this.f$1, this.f$2);
    }
}
