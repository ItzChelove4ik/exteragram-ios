package org.telegram.ui.Components.Premium.boosts;

import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftInfoBottomSheet$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Browser.Progress f$3;

    public /* synthetic */ GiftInfoBottomSheet$$ExternalSyntheticLambda1(AtomicBoolean r1, BaseFragment r2, String r3, Browser.Progress r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        GiftInfoBottomSheet.$r8$lambda$YUYLW_38Gm6o4KeCIm9DBgReAdw(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.TL_payments_checkedGiftCode) r4);
    }
}
