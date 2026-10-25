package org.telegram.ui.Components.poll;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes7.dex */
public final /* synthetic */ class WebPageLoader$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ WebPageLoader f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ WebPageLoader$$ExternalSyntheticLambda0(WebPageLoader r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        WebPageLoader.$r8$lambda$GUKg2kx00eBFfAKgY82y77RKqNA(this.f$0, this.f$1, (TL_account.webPagePreview) r2, (TLRPC.TL_error) r3);
    }
}
