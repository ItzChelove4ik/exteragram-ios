package org.telegram.ui.Stories;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda37 implements StoryPrivacyBottomSheet.DoneCallback {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ StoryPrivacyBottomSheet f$1;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda37(PeerStoriesView.AnonymousClass8 r1, StoryPrivacyBottomSheet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet.DoneCallback
    public final void done(StoryPrivacyBottomSheet.StoryPrivacy r12, boolean r13, boolean r14, boolean r15, boolean r16, TLRPC.InputPeer r17, int r18, Runnable r19, Runnable r20) {
        PeerStoriesView.AnonymousClass8.$r8$lambda$6W8gYdA48uK10i8YV1p_hH4il18(this.f$0, this.f$1, r12, r13, r14, r15, r16, r17, r18, r19, r20);
    }
}
