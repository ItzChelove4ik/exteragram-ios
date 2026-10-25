package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda6(PeerStoriesView.AnonymousClass8 r1, TL_stories.StoryItem r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        PeerStoriesView.AnonymousClass8.$r8$lambda$T7TFN1lIBFGlHqXfI5RBdtCL_XI(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
