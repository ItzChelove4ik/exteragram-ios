package org.telegram.ui;

import android.content.DialogInterface;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda15 implements DialogInterface.OnClickListener {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TL_keyboard.TL_inlineButtonTypeCallback f$2;
    public final /* synthetic */ TL_keyboard.TL_inlineButtonTypeSwitchInline f$3;
    public final /* synthetic */ TL_keyboard.TL_inlineButtonTypeUserProfile f$4;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda15(ChatActivity.ChatMessageCellDelegate r1, String r2, TL_keyboard.TL_inlineButtonTypeCallback r3, TL_keyboard.TL_inlineButtonTypeSwitchInline r4, TL_keyboard.TL_inlineButtonTypeUserProfile r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r8, int r9) {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$hWuGfK2wK0wICN5HJwJEmP4WikA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
