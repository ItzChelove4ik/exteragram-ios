package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStarsController$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ BotStarsController f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ BotStarsController$$ExternalSyntheticLambda5(BotStarsController r1, AlertDialog r2, long r3, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        BotStarsController.$r8$lambda$yjVf6zcQZpNNr_6t5lCoW5zKPeU(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
