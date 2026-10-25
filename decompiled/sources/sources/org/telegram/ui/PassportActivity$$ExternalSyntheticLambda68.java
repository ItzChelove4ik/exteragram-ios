package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$$ExternalSyntheticLambda68 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ PassportActivity f$0;
    public final /* synthetic */ TLRPC.TL_secureRequiredType f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ PassportActivity$$ExternalSyntheticLambda68(PassportActivity r1, TLRPC.TL_secureRequiredType r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        PassportActivity.$r8$lambda$Ef1ttKbDd7WXRoiadXlUzwEfb3w(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
