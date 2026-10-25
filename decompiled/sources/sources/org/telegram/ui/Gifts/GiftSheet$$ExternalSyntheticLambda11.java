package org.telegram.ui.Gifts;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.ui.Stars.StarGiftSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftSheet$$ExternalSyntheticLambda11 implements Utilities.Callback {
    public final /* synthetic */ GiftSheet f$0;
    public final /* synthetic */ StarGiftSheet f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ GiftSheet$$ExternalSyntheticLambda11(GiftSheet r1, StarGiftSheet r2, long r3, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        GiftSheet.$r8$lambda$8SDKcdZyeZL8uolQ_sqTBOslkMc(this.f$0, this.f$1, this.f$2, this.f$3, (Browser.Progress) r7);
    }
}
