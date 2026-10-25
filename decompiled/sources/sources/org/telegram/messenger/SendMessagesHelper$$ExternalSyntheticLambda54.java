package org.telegram.messenger;

import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChatActivity;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda54 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ TL_keyboard.KeyboardButtonProto f$3;
    public final /* synthetic */ ChatActivity f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda54(SendMessagesHelper r1, boolean r2, MessageObject r3, TL_keyboard.KeyboardButtonProto r4, ChatActivity r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r8, int r9) {
        SendMessagesHelper.$r8$lambda$ldWNiCLtl9Ckk2pe1doFDEt7zGk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
