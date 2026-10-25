package org.telegram.ui.Stories;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$8$$ExternalSyntheticLambda51 implements View.OnClickListener {
    public final /* synthetic */ PeerStoriesView.AnonymousClass8 f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;
    public final /* synthetic */ StoryViewer f$2;
    public final /* synthetic */ TLRPC.Chat f$3;

    public /* synthetic */ PeerStoriesView$8$$ExternalSyntheticLambda51(PeerStoriesView.AnonymousClass8 r1, TL_stories.StoryItem r2, StoryViewer r3, TLRPC.Chat r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r4) {
        PeerStoriesView.AnonymousClass8.m19607$r8$lambda$KNBDS3nX4u9u7cmzkafWXy6unU(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
