package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChangeUsernameActivity$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ ChangeUsernameActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TL_account.updateUsername f$3;

    public /* synthetic */ ChangeUsernameActivity$$ExternalSyntheticLambda8(ChangeUsernameActivity r1, AlertDialog r2, TLRPC.TL_error r3, TL_account.updateUsername r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChangeUsernameActivity.$r8$lambda$jsp9hhvEnr0_vUQ0UOX5EysEU7o(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
