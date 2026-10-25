package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class TermsOfServiceView$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ TermsOfServiceView f$0;
    public final /* synthetic */ AlertDialog f$1;

    public /* synthetic */ TermsOfServiceView$$ExternalSyntheticLambda6(TermsOfServiceView r1, AlertDialog r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        TermsOfServiceView.$r8$lambda$0ceETEcLxiCcxdHHGAN6f2ptKlU(this.f$0, this.f$1, r2, r3);
    }
}
