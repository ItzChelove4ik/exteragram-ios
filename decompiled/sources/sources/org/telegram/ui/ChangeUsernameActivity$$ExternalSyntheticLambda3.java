package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChangeUsernameActivity$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ ChangeUsernameActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TL_account.updateUsername f$2;

    public /* synthetic */ ChangeUsernameActivity$$ExternalSyntheticLambda3(ChangeUsernameActivity r1, AlertDialog r2, TL_account.updateUsername r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChangeUsernameActivity.m7142$r8$lambda$XaRoXjGXHXO3jZucqjODHeOx28(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
