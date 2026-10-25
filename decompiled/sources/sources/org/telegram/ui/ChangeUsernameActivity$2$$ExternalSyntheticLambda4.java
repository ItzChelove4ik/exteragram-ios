package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChangeUsernameActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChangeUsernameActivity$2$$ExternalSyntheticLambda4 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChangeUsernameActivity.AnonymousClass2 f$0;
    public final /* synthetic */ TLRPC.TL_username f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ ChangeUsernameActivity$2$$ExternalSyntheticLambda4(ChangeUsernameActivity.AnonymousClass2 r1, TLRPC.TL_username r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        ChangeUsernameActivity.AnonymousClass2.$r8$lambda$bQC9jpnxIMDNbFAsaaGYNf1k8Wo(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
