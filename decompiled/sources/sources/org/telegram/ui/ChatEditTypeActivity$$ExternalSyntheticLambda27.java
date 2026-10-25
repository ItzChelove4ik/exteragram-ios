package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditTypeActivity$$ExternalSyntheticLambda27 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatEditTypeActivity f$0;
    public final /* synthetic */ TLRPC.Chat f$1;

    public /* synthetic */ ChatEditTypeActivity$$ExternalSyntheticLambda27(ChatEditTypeActivity r1, TLRPC.Chat r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        ChatEditTypeActivity.$r8$lambda$Cwkv0Sdt7Csl7dkWQ1kFf7cGVpE(this.f$0, this.f$1, r2, r3);
    }
}
