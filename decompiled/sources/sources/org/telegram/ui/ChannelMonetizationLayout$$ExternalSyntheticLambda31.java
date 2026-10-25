package org.telegram.ui;

import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelMonetizationLayout$$ExternalSyntheticLambda31 implements Utilities.Callback {
    public final /* synthetic */ ChannelMonetizationLayout f$0;
    public final /* synthetic */ LimitReachedBottomSheet f$1;

    public /* synthetic */ ChannelMonetizationLayout$$ExternalSyntheticLambda31(ChannelMonetizationLayout r1, LimitReachedBottomSheet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChannelMonetizationLayout.$r8$lambda$pZXgeHD3q2_A8BuUaEtC2IhjJd8(this.f$0, this.f$1, (ChannelBoostsController.CanApplyBoost) r2);
    }
}
