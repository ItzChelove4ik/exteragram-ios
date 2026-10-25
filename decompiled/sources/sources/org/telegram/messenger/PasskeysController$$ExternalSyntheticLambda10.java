package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class PasskeysController$$ExternalSyntheticLambda10 implements Utilities.Callback2 {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ PasskeysController$$ExternalSyntheticLambda10(AlertDialog r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        PasskeysController.$r8$lambda$9XpDvasej2ZlaH5A73JOyh8A3uo(this.f$0, this.f$1, (TL_account.Passkey) r2, (TLRPC.TL_error) r3);
    }
}
