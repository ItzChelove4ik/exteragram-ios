package org.telegram.messenger;

import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChannelBoostsController$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ ChannelBoostsController.CanApplyBoost f$0;
    public final /* synthetic */ TL_stories.TL_premium_boostsStatus f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ ChannelBoostsController$$ExternalSyntheticLambda1(ChannelBoostsController.CanApplyBoost r1, TL_stories.TL_premium_boostsStatus r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        ChannelBoostsController.m3915$r8$lambda$v43EtJeDdmKTu6HKdG4hxOhP4(this.f$0, this.f$1, this.f$2, (TL_stories.TL_premium_myBoosts) r3);
    }
}
