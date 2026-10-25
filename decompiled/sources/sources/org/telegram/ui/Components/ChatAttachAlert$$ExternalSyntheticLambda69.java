package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatAttachAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlert$$ExternalSyntheticLambda69 implements RequestDelegate {
    public final /* synthetic */ ChatAttachAlert f$0;
    public final /* synthetic */ ChatAttachAlert.AttachBotButton f$1;

    public /* synthetic */ ChatAttachAlert$$ExternalSyntheticLambda69(ChatAttachAlert r1, ChatAttachAlert.AttachBotButton r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChatAttachAlert.$r8$lambda$9udQg1qdbffgsh0Vcg7EAOtPYaU(this.f$0, this.f$1, r2, r3);
    }
}
