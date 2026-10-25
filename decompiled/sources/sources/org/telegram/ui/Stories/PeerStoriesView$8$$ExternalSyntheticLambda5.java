package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ StoriesController.BotPreviewsList f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda5(StoriesController.BotPreviewsList r1, TL_stories.StoryItem r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PeerStoriesView.AnonymousClass8.m19613$r8$lambda$_yNBXYv7gOPVoHtzsMoD1J64fI(this.f$0, this.f$1, this.f$2);
    }
}
