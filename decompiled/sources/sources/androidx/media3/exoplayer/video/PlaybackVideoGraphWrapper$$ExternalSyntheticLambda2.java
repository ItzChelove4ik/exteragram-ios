package androidx.media3.exoplayer.video;

import androidx.media3.common.util.HandlerWrapper;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final /* synthetic */ class PlaybackVideoGraphWrapper$$ExternalSyntheticLambda2 implements Executor {
    public final /* synthetic */ HandlerWrapper f$0;

    public /* synthetic */ PlaybackVideoGraphWrapper$$ExternalSyntheticLambda2(HandlerWrapper r1) {
        this.f$0 = r1;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r1) {
        this.f$0.post(r1);
    }
}
