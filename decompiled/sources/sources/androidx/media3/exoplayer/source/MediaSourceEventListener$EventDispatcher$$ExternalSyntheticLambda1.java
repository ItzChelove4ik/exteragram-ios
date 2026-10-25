package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.MediaSourceEventListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f$0;
    public final /* synthetic */ LoadEventInfo f$1;
    public final /* synthetic */ MediaLoadData f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda1(MediaSourceEventListener.EventDispatcher r1, LoadEventInfo r2, MediaLoadData r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r4) {
        MediaSourceEventListener.EventDispatcher.$r8$lambda$RGKaOtjX2KMgFilg5dOIxU5Zg1o(this.f$0, this.f$1, this.f$2, this.f$3, (MediaSourceEventListener) r4);
    }
}
