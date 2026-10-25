package org.telegram.ui.web;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda62 implements Runnable {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ String[] f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ AlertDialog f$4;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda62(BotWebViewContainer r1, TLObject r2, String[] r3, TLRPC.TL_error r4, AlertDialog r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BotWebViewContainer.$r8$lambda$v_1LcymqnDRkLB1FjL3K34MvFcQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
