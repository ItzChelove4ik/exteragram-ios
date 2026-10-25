package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.MediaSourceEventListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ MediaSourceEventListener f$1;

    public /* synthetic */ MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda6(Consumer r1, MediaSourceEventListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSourceEventListener.EventDispatcher.$r8$lambda$6pNO9KxCp680_uXaxiMfgnUnpBM(this.f$0, this.f$1);
    }
}
