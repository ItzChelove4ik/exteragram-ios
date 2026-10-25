package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class LocationActivity$$ExternalSyntheticLambda34 implements RequestDelegate {
    public final /* synthetic */ LocationActivity f$0;
    public final /* synthetic */ AlertDialog[] f$1;
    public final /* synthetic */ TLRPC.TL_messageMediaVenue f$2;

    public /* synthetic */ LocationActivity$$ExternalSyntheticLambda34(LocationActivity r1, AlertDialog[] r2, TLRPC.TL_messageMediaVenue r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        LocationActivity.$r8$lambda$R6YVxy1iFvXDhfiqtg80hU5A1dI(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
