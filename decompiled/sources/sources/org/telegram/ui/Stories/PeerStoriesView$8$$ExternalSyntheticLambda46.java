package org.telegram.ui.Stories;

import android.app.Activity;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda46 implements Runnable {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ Activity f$1;
    public final /* synthetic */ TL_stories.StoryItem f$2;
    public final /* synthetic */ StoryViewer f$3;
    public final /* synthetic */ PeerStoriesView.SharedResources f$4;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda46(PeerStoriesView.AnonymousClass8 r1, Activity r2, TL_stories.StoryItem r3, StoryViewer r4, PeerStoriesView.SharedResources r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PeerStoriesView.AnonymousClass8.m19623$r8$lambda$xmngqCPBsWTUhsiM0e2_zMyMa4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
