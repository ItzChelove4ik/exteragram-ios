package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatRightsEditActivity$$ExternalSyntheticLambda16 implements MessagesController.ErrorDelegate {
    public final /* synthetic */ ChatRightsEditActivity f$0;

    public /* synthetic */ ChatRightsEditActivity$$ExternalSyntheticLambda16(ChatRightsEditActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.MessagesController.ErrorDelegate
    public final boolean run(TLRPC.TL_error r1) {
        return ChatRightsEditActivity.$r8$lambda$4XyyDLCe4eyl2MnyglXkJDHF2N4(this.f$0, r1);
    }
}
