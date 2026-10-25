package org.telegram.ui.Stars;

import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda192 implements RequestDelegate {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ AlertDialog f$2;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ CharSequence f$5;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda192(StarGiftSheet r1, Browser.Progress r2, AlertDialog r3, TL_stars.TL_starGiftUnique r4, long r5, CharSequence r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        StarGiftSheet.m18976$r8$lambda$Xl2zeLlw4fFxlGm4074ssy58p4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r10, r11);
    }
}
