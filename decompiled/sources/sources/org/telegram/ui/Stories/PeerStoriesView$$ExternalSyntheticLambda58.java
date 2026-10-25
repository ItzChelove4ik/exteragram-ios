package org.telegram.ui.Stories;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$$ExternalSyntheticLambda58 implements Runnable {
    public final /* synthetic */ PeerStoriesView f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TL_stories.StoryItem f$3;
    public final /* synthetic */ StoryPrivacyBottomSheet.StoryPrivacy f$4;

    public /* synthetic */ PeerStoriesView$$ExternalSyntheticLambda58(PeerStoriesView r1, Runnable r2, TLRPC.TL_error r3, TL_stories.StoryItem r4, StoryPrivacyBottomSheet.StoryPrivacy r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PeerStoriesView.m19439$r8$lambda$cf_06TVVv7__CbfQXiWcZqoxg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
