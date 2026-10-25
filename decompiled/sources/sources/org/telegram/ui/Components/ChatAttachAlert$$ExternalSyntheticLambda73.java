package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlert$$ExternalSyntheticLambda73 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatAttachAlert f$0;
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ ChatAttachAlert$$ExternalSyntheticLambda73(ChatAttachAlert r1, TLRPC.TL_attachMenuBot r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        ChatAttachAlert.m9413$r8$lambda$aNu9lYGVeCHf5k3DViLhkCya7M(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
