package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda53 implements RequestDelegate {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ StoryPrivacyBottomSheet f$1;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda53(PeerStoriesView.AnonymousClass8 r1, StoryPrivacyBottomSheet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PeerStoriesView.AnonymousClass8.$r8$lambda$u7h6FzqOJBSbWbgkeN9UBuKztIM(this.f$0, this.f$1, r2, r3);
    }
}
