package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.MediaSourceEventListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f$0;
    public final /* synthetic */ MediaLoadData f$1;

    public /* synthetic */ MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda0(MediaSourceEventListener.EventDispatcher r1, MediaLoadData r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r2) {
        MediaSourceEventListener.EventDispatcher.m838$r8$lambda$ZIHKe_Lg1hh0l8iqVf4fKSaIDw(this.f$0, this.f$1, (MediaSourceEventListener) r2);
    }
}
