package androidx.media3.exoplayer.video;

import androidx.media3.common.VideoSize;
import androidx.media3.exoplayer.video.DefaultVideoSink;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultVideoSink$FrameRendererImpl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ DefaultVideoSink.FrameRendererImpl f$0;
    public final /* synthetic */ VideoSize f$1;

    public /* synthetic */ DefaultVideoSink$FrameRendererImpl$$ExternalSyntheticLambda2(DefaultVideoSink.FrameRendererImpl r1, VideoSize r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DefaultVideoSink.FrameRendererImpl.$r8$lambda$aePYIxVMt8t6uIUdqIaOlLCNIlY(this.f$0, this.f$1);
    }
}
