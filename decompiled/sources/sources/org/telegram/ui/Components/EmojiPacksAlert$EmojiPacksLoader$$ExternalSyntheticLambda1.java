package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiPacksAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ EmojiPacksAlert.EmojiPacksLoader f$0;
    public final /* synthetic */ boolean[] f$1;

    public /* synthetic */ EmojiPacksAlert$EmojiPacksLoader$$ExternalSyntheticLambda1(EmojiPacksAlert.EmojiPacksLoader r1, boolean[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        EmojiPacksAlert.EmojiPacksLoader.$r8$lambda$6sKouT2_FgCmmDhPbC4qUDMD6lI(this.f$0, this.f$1, (TLRPC.TL_messages_stickerSet) r2);
    }
}
