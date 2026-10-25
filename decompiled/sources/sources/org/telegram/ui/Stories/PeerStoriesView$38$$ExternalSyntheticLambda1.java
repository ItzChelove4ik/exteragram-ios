package org.telegram.ui.Stories;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$38$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ PeerStoriesView.AnonymousClass38 f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ ReactionsLayoutInBubble.VisibleReaction f$2;
    public final /* synthetic */ View f$3;

    public /* synthetic */ PeerStoriesView$38$$ExternalSyntheticLambda1(PeerStoriesView.AnonymousClass38 r1, boolean r2, ReactionsLayoutInBubble.VisibleReaction r3, View r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        PeerStoriesView.AnonymousClass38.m19597$r8$lambda$dkWTqfgsO8iz93BknNIsZtixHY(this.f$0, this.f$1, this.f$2, this.f$3, (Long) r4);
    }
}
