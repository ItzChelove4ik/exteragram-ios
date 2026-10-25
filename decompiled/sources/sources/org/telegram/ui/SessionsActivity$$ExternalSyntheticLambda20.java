package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class SessionsActivity$$ExternalSyntheticLambda20 implements RequestDelegate {
    public final /* synthetic */ SessionsActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLRPC.TL_authorization f$2;

    public /* synthetic */ SessionsActivity$$ExternalSyntheticLambda20(SessionsActivity r1, AlertDialog r2, TLRPC.TL_authorization r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        SessionsActivity.$r8$lambda$64iFicpthKD3r3Uw9dh0juS5ecI(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
