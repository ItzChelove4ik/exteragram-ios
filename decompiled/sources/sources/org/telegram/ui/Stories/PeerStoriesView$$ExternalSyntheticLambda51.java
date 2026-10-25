package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$$ExternalSyntheticLambda51 implements RequestDelegate {
    public final /* synthetic */ PeerStoriesView f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ TL_stories.StoryItem f$2;
    public final /* synthetic */ StoryPrivacyBottomSheet.StoryPrivacy f$3;

    public /* synthetic */ PeerStoriesView$$ExternalSyntheticLambda51(PeerStoriesView r1, Runnable r2, TL_stories.StoryItem r3, StoryPrivacyBottomSheet.StoryPrivacy r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        PeerStoriesView.$r8$lambda$Wb47ShyNFTzpt6g3X1rq9FKVKzs(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
