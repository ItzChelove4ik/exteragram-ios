package org.telegram.ui.Stories;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$$ExternalSyntheticLambda40 implements StoryPrivacyBottomSheet.DoneCallback {
    public final /* synthetic */ PeerStoriesView f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;

    public /* synthetic */ PeerStoriesView$$ExternalSyntheticLambda40(PeerStoriesView r1, TL_stories.StoryItem r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet.DoneCallback
    public final void done(StoryPrivacyBottomSheet.StoryPrivacy r12, boolean r13, boolean r14, boolean r15, boolean r16, TLRPC.InputPeer r17, int r18, Runnable r19, Runnable r20) {
        PeerStoriesView.$r8$lambda$1XDBPAFVIL65g_QKQe6Ak4MfJmY(this.f$0, this.f$1, r12, r13, r14, r15, r16, r17, r18, r19, r20);
    }
}
