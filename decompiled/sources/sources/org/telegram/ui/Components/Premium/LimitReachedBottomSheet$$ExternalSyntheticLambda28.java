package org.telegram.ui.Components.Premium;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.Loadable;

/* loaded from: classes7.dex */
public final /* synthetic */ class LimitReachedBottomSheet$$ExternalSyntheticLambda28 implements Utilities.Callback {
    public final /* synthetic */ LimitReachedBottomSheet f$0;
    public final /* synthetic */ Loadable f$1;
    public final /* synthetic */ TL_stories.TL_premium_myBoosts f$2;

    public /* synthetic */ LimitReachedBottomSheet$$ExternalSyntheticLambda28(LimitReachedBottomSheet r1, Loadable r2, TL_stories.TL_premium_myBoosts r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        LimitReachedBottomSheet.m11987$r8$lambda$fOrjyoAmbGhWNNhHP5PJVyKtGk(this.f$0, this.f$1, this.f$2, (TL_stories.TL_premium_boostsStatus) r3);
    }
}
