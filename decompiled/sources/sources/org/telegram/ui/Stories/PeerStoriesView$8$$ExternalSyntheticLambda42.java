package org.telegram.ui.Stories;

import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda42 implements Runnable {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ Theme.ResourcesProvider f$1;
    public final /* synthetic */ TL_stories.StoryItem f$2;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda42(PeerStoriesView.AnonymousClass8 r1, Theme.ResourcesProvider r2, TL_stories.StoryItem r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PeerStoriesView.AnonymousClass8.m19608$r8$lambda$Omm4uj9qQqwBBIf4pXPWBfLXv4(this.f$0, this.f$1, this.f$2);
    }
}
