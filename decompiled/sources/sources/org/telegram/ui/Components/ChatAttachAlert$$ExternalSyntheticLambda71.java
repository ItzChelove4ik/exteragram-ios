package org.telegram.ui.Components;

import org.telegram.messenger.MessageSuggestionParams;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ChatActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlert$$ExternalSyntheticLambda71 implements Utilities.Callback {
    public final /* synthetic */ ChatAttachAlert f$0;
    public final /* synthetic */ ChatActivity f$1;

    public /* synthetic */ ChatAttachAlert$$ExternalSyntheticLambda71(ChatAttachAlert r1, ChatActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChatAttachAlert.$r8$lambda$w5aai3dnK5ISPeH_vJsqZEr32uE(this.f$0, this.f$1, (MessageSuggestionParams) r2);
    }
}
