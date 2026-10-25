package org.telegram.ui.bots;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AffiliateProgramFragment$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ AffiliateProgramFragment f$0;
    public final /* synthetic */ AlertDialog f$1;

    public /* synthetic */ AffiliateProgramFragment$$ExternalSyntheticLambda10(AffiliateProgramFragment r1, AlertDialog r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        AffiliateProgramFragment.m21305$r8$lambda$wZvurZXLNvcWCG_sCvwR1aWyfI(this.f$0, this.f$1, r2, r3);
    }
}
