package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda7 implements Utilities.Callback {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda7(PeerStoriesView.AnonymousClass8 r1, TL_stories.StoryItem r2, Theme.ResourcesProvider r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        PeerStoriesView.AnonymousClass8.$r8$lambda$3vPD5M6du5R37pCZM_SnvaAToIk(this.f$0, this.f$1, this.f$2, (StoriesController.StoryAlbum) r3);
    }
}
