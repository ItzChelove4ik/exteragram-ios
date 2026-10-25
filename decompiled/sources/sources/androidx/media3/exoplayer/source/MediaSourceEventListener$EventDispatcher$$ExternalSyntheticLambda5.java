package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f$0;
    public final /* synthetic */ MediaSource.MediaPeriodId f$1;
    public final /* synthetic */ MediaLoadData f$2;

    public /* synthetic */ MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda5(MediaSourceEventListener.EventDispatcher r1, MediaSource.MediaPeriodId r2, MediaLoadData r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r3) {
        MediaSourceEventListener.EventDispatcher.$r8$lambda$SyhE931IzGC5Mv4nKNFYLEFNf1Y(this.f$0, this.f$1, this.f$2, (MediaSourceEventListener) r3);
    }
}
