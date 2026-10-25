package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class GiftOfferSheet$$ExternalSyntheticLambda1 implements Utilities.Callback2 {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ Browser.Progress f$2;
    public final /* synthetic */ AlertDialog f$3;

    public /* synthetic */ GiftOfferSheet$$ExternalSyntheticLambda1(int r1, BaseFragment r2, Browser.Progress r3, AlertDialog r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        GiftOfferSheet.m18892$r8$lambda$IzHiAyep2_oXhadHlpCz1iaQM(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.Updates) r7, (TLRPC.TL_error) r8);
    }
}
