package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectChatUserSheet$$ExternalSyntheticLambda0 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ SelectChatUserSheet f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ SelectChatUserSheet$$ExternalSyntheticLambda0(SelectChatUserSheet r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        SelectChatUserSheet.$r8$lambda$Dx7NhAX1FvVXkZ1tSctC8CXLVCQ(this.f$0, this.f$1, r2, r3);
    }
}
