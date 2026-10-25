package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.recorder.PaintView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PaintView$24$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TLRPC.MessageMedia f$0;
    public final /* synthetic */ TL_stories.TL_mediaAreaGeoPoint f$1;

    public /* synthetic */ PaintView$24$$ExternalSyntheticLambda0(TLRPC.MessageMedia r1, TL_stories.TL_mediaAreaGeoPoint r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PaintView.AnonymousClass24.m20140$r8$lambda$TJAqtHJ3gUHTAs7IV4QJfxXHk(this.f$0, this.f$1);
    }
}
