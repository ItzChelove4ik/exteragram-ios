package org.telegram.ui.Components.Premium.boosts.adapters;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftInfoAdapter$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ GiftInfoAdapter f$0;

    public /* synthetic */ GiftInfoAdapter$$ExternalSyntheticLambda0(GiftInfoAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.onObjectClicked((TLObject) r1);
    }
}
