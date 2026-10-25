package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import java.io.IOException;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f$0;
    public final /* synthetic */ LoadEventInfo f$1;
    public final /* synthetic */ MediaLoadData f$2;
    public final /* synthetic */ IOException f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda3(MediaSourceEventListener.EventDispatcher r1, LoadEventInfo r2, MediaLoadData r3, IOException r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r7) {
        MediaSourceEventListener.EventDispatcher.$r8$lambda$MkDhO1PQrN_SYW5nSwDKOMrpzXg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (MediaSourceEventListener) r7);
    }
}
