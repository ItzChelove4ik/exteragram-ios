package org.telegram.ui.Stars;

import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda117 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$2;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda117(StarGiftSheet r1, Browser.Progress r2, TL_stars.TL_starGiftUnique r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        StarGiftSheet.$r8$lambda$n9CdV5sdmfWLc7RbeYsicYMZrJk(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
