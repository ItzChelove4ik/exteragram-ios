package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class FactCheckController$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ FactCheckController f$0;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ AlertDialog f$3;

    public /* synthetic */ FactCheckController$$ExternalSyntheticLambda0(FactCheckController r1, TLRPC.TL_textWithEntities r2, boolean r3, AlertDialog r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        FactCheckController.m3997$r8$lambda$Zj8KJppbhb3_WpMVDIia5RYMME(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
