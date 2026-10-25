package org.telegram.ui;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChangeUsernameActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChangeUsernameActivity$2$$ExternalSyntheticLambda0 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChangeUsernameActivity.AnonymousClass2 f$0;
    public final /* synthetic */ TLRPC.TL_username f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ View f$3;

    public /* synthetic */ ChangeUsernameActivity$2$$ExternalSyntheticLambda0(ChangeUsernameActivity.AnonymousClass2 r1, TLRPC.TL_username r2, int r3, View r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r7, int r8) {
        ChangeUsernameActivity.AnonymousClass2.$r8$lambda$FPNZwYSXILItJ6y6yJhiHC7Mg7s(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
