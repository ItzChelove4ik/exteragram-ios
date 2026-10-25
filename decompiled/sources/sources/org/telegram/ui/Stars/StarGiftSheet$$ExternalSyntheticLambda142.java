package org.telegram.ui.Stars;

import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda142 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ TL_stories.TL_premium_boostsStatus f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ MessagesController f$3;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda142(StarGiftSheet r1, TL_stories.TL_premium_boostsStatus r2, long r3, MessagesController r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        StarGiftSheet.$r8$lambda$ayz9CeZSNfCTkjyo3yWyDyXn1pE(this.f$0, this.f$1, this.f$2, this.f$3, (ChannelBoostsController.CanApplyBoost) r7);
    }
}
