package org.telegram.ui.Gifts;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stars.StarGiftSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftSheet$$ExternalSyntheticLambda23 implements Utilities.Callback {
    public final /* synthetic */ GiftSheet f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ StarGiftSheet f$3;

    public /* synthetic */ GiftSheet$$ExternalSyntheticLambda23(GiftSheet r1, Browser.Progress r2, Utilities.Callback r3, StarGiftSheet r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        GiftSheet.$r8$lambda$js4A9A0J_tqDu3NNZMKZ33nxjKs(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.TL_error) r4);
    }
}
