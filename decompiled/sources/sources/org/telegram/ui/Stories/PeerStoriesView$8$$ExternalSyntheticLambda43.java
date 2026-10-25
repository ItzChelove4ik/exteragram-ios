package org.telegram.ui.Stories;

import java.util.HashSet;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda43 implements Utilities.Callback {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ HashSet f$1;
    public final /* synthetic */ TL_stories.StoryItem f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda43(PeerStoriesView.AnonymousClass8 r1, HashSet r2, TL_stories.StoryItem r3, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        PeerStoriesView.AnonymousClass8.m19616$r8$lambda$iqv39MkwqFBWymPm0bp7TPpbQ(this.f$0, this.f$1, this.f$2, this.f$3, (StoriesController.StoryAlbum) r4);
    }
}
