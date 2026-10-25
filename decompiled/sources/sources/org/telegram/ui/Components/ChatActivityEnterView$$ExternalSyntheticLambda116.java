package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda116 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatActivityEnterView f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ TL_keyboard.KeyboardButtonProto f$2;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda116(ChatActivityEnterView r1, MessageObject r2, TL_keyboard.KeyboardButtonProto r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        ChatActivityEnterView.m9146$r8$lambda$qlapcleZsJ1EMhU311e0EUs0SI(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
