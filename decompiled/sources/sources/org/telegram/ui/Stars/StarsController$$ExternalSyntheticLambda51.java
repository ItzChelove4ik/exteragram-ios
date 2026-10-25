package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda51 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TL_stars.InputSavedStarGift f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda51(StarsController r1, AlertDialog r2, TL_stars.InputSavedStarGift r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StarsController.$r8$lambda$pCPEhGzb_GFPjgRpCoofiz0w4wE(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
