package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.PeerStoriesView;
import org.telegram.ui.Stories.StoryCaptionView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$5$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ PeerStoriesView.AnonymousClass5 f$0;
    public final /* synthetic */ StoryCaptionView.Panel f$1;
    public final /* synthetic */ StoryViewer f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;

    public /* synthetic */ PeerStoriesView$5$$ExternalSyntheticLambda2(PeerStoriesView.AnonymousClass5 r1, StoryCaptionView.Panel r2, StoryViewer r3, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        PeerStoriesView.AnonymousClass5.m19598$r8$lambda$19qLgRzKCZ015MUqoH4xCfh6bo(this.f$0, this.f$1, this.f$2, this.f$3, (TL_stories.StoryItem) r4);
    }
}
