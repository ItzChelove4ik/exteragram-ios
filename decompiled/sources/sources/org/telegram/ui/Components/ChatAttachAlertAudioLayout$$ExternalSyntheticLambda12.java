package org.telegram.ui.Components;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertAudioLayout$$ExternalSyntheticLambda12 implements Utilities.Callback2 {
    public final /* synthetic */ ChatAttachAlertAudioLayout f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ ChatAttachAlertAudioLayout$$ExternalSyntheticLambda12(ChatAttachAlertAudioLayout r1, MessagesController r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        ChatAttachAlertAudioLayout.$r8$lambda$snpivLdi_zkvzeKeBluSUuwkg9M(this.f$0, this.f$1, this.f$2, (TLRPC.messages_BotResults) r3, (TLRPC.TL_error) r4);
    }
}
