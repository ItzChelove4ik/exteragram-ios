package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiPacksAlert$$ExternalSyntheticLambda11 implements Utilities.Callback {
    public final /* synthetic */ EmojiPacksAlert f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$2;

    public /* synthetic */ EmojiPacksAlert$$ExternalSyntheticLambda11(EmojiPacksAlert r1, AlertDialog r2, TLRPC.TL_messages_stickerSet r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        EmojiPacksAlert.$r8$lambda$c9EZH4JLP45T4EFagPkVYWyPeM4(this.f$0, this.f$1, this.f$2, (TLRPC.User) r3);
    }
}
