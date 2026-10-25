package org.telegram.ui;

import android.content.Context;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda188 implements MessagesController.ErrorDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda188(ChatActivity r1, Context r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesController.ErrorDelegate
    public final boolean run(TLRPC.TL_error r2) {
        return ChatActivity.$r8$lambda$_1z1ogyNAsocgdILgIWxYAivOCs(this.f$0, this.f$1, r2);
    }
}
