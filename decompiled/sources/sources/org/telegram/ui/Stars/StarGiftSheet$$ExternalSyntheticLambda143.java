package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda143 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda143(StarGiftSheet r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        StarGiftSheet.m18953$r8$lambda$6wRL0_QETb_W7PDjwsaqC39U_w(this.f$0, this.f$1, (TL_stars.starGiftUpgradePreview) r2);
    }
}
