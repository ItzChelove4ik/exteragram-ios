package org.telegram.ui.Components.Premium.boosts;

import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftInfoBottomSheet$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ Browser.Progress f$1;

    public /* synthetic */ GiftInfoBottomSheet$$ExternalSyntheticLambda2(AtomicBoolean r1, Browser.Progress r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        GiftInfoBottomSheet.$r8$lambda$Y7glcthtbPhwh6Sao6m83JvUN7w(this.f$0, this.f$1, (TLRPC.TL_error) r2);
    }
}
