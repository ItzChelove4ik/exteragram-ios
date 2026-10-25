package org.telegram.ui.Stories;

import android.view.View;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda20 implements View.OnClickListener {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ StoryPrivacyBottomSheet.StoryPrivacy f$1;
    public final /* synthetic */ TL_stories.StoryItem f$2;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda20(PeerStoriesView.AnonymousClass8 r1, StoryPrivacyBottomSheet.StoryPrivacy r2, TL_stories.StoryItem r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r3) {
        PeerStoriesView.AnonymousClass8.$r8$lambda$kS6wJp3Urrglu0z2Ef8eGulSplc(this.f$0, this.f$1, this.f$2, r3);
    }
}
