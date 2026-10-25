package org.telegram.ui.Stories;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class ViewsForPeerStoriesRequester$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ViewsForPeerStoriesRequester f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TL_stories.TL_stories_getStoriesViews f$2;

    public /* synthetic */ ViewsForPeerStoriesRequester$$ExternalSyntheticLambda2(ViewsForPeerStoriesRequester r1, TLObject r2, TL_stories.TL_stories_getStoriesViews r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ViewsForPeerStoriesRequester.$r8$lambda$3XURm3xmb9eZiTQ4XBPCAOBgiZQ(this.f$0, this.f$1, this.f$2);
    }
}
