package org.telegram.ui;

import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class PasskeysActivity$$ExternalSyntheticLambda9 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ PasskeysActivity f$0;
    public final /* synthetic */ TL_account.Passkey f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ PasskeysActivity$$ExternalSyntheticLambda9(PasskeysActivity r1, TL_account.Passkey r2, String r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r7, int r8) {
        PasskeysActivity.$r8$lambda$W0aSn4QiyqubXTDyVZhckUsEopE(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
