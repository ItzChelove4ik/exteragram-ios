package org.telegram.ui.Stories;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda59 implements MessagesStorage.StringCallback {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda59(PeerStoriesView.AnonymousClass8 r1, TL_stories.StoryItem r2, Theme.ResourcesProvider r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.MessagesStorage.StringCallback
    public final void run(String r3) {
        PeerStoriesView.AnonymousClass8.$r8$lambda$U7EbgAgmMCeQNjtKogfzlVNd5U8(this.f$0, this.f$1, this.f$2, r3);
    }
}
