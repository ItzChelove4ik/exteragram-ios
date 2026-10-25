package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda149 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ Long f$1;
    public final /* synthetic */ UserSelectorBottomSheet[] f$2;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda149(StarGiftSheet r1, Long r2, UserSelectorBottomSheet[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        StarGiftSheet.$r8$lambda$2ceGCNsCKl01JuI2OL3XsmJbTxY(this.f$0, this.f$1, this.f$2, (Browser.Progress) r3);
    }
}
