package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.GroupCallActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$EmojiSlot$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ GroupCallActivity.EmojiSlot f$0;

    public /* synthetic */ GroupCallActivity$EmojiSlot$$ExternalSyntheticLambda0(GroupCallActivity.EmojiSlot r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        GroupCallActivity.EmojiSlot.$r8$lambda$qwTHbxkUGVdAtDhZN44bHwlh8XE(this.f$0, (TLRPC.TL_messages_stickerSet) r1);
    }
}
