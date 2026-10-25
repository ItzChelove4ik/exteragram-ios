package org.telegram.ui.Stars;

import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftOfferSheet$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ GiftOfferSheet f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ AlertDialog f$2;
    public final /* synthetic */ TLRPC.Updates f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ GiftOfferSheet$$ExternalSyntheticLambda13(GiftOfferSheet r1, Browser.Progress r2, AlertDialog r3, TLRPC.Updates r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GiftOfferSheet.$r8$lambda$m5aaIZxJ6ocDKRKbZh4dRKRfAa8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
