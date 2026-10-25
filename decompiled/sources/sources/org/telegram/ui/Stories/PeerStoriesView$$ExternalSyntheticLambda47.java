package org.telegram.ui.Stories;

import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class PeerStoriesView$$ExternalSyntheticLambda47 implements Utilities.Callback {
    public final /* synthetic */ PeerStoriesView f$0;
    public final /* synthetic */ TL_stories.TL_premium_boostsStatus f$1;

    public /* synthetic */ PeerStoriesView$$ExternalSyntheticLambda47(PeerStoriesView r1, TL_stories.TL_premium_boostsStatus r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        PeerStoriesView.m19433$r8$lambda$F_nRmlkXpd8b5Oy0awO926MxC0(this.f$0, this.f$1, (ChannelBoostsController.CanApplyBoost) r2);
    }
}
