package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda175 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ UserSelectorBottomSheet[] f$2;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda175(StarGiftSheet r1, Browser.Progress r2, UserSelectorBottomSheet[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        StarGiftSheet.$r8$lambda$yjNNR41cKIq_sDnJxm2U72VIg3M(this.f$0, this.f$1, this.f$2, (TLRPC.TL_error) r3);
    }
}
