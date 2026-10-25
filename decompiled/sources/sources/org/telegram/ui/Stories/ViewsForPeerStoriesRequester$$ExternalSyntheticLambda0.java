package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class ViewsForPeerStoriesRequester$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ ViewsForPeerStoriesRequester f$0;
    public final /* synthetic */ TL_stories.TL_stories_getStoriesViews f$1;

    public /* synthetic */ ViewsForPeerStoriesRequester$$ExternalSyntheticLambda0(ViewsForPeerStoriesRequester r1, TL_stories.TL_stories_getStoriesViews r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ViewsForPeerStoriesRequester.m19836$r8$lambda$e9eVLu_Y904viuIvl9dVr9FZRo(this.f$0, this.f$1, r2, r3);
    }
}
