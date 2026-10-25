package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TL_stories.StoryItem f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda8(PeerStoriesView.AnonymousClass8 r1, TLObject r2, TL_stories.StoryItem r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PeerStoriesView.AnonymousClass8.m19612$r8$lambda$_UfHcucuiDNsSWCrfiX9PriY(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
