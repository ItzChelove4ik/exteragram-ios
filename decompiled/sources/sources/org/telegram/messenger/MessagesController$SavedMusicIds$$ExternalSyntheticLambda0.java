package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$SavedMusicIds$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ MessagesController.SavedMusicIds f$0;

    public /* synthetic */ MessagesController$SavedMusicIds$$ExternalSyntheticLambda0(MessagesController.SavedMusicIds r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        MessagesController.SavedMusicIds.$r8$lambda$fPswyaPcKuUWefEJo2Kqaf852FE(this.f$0, r1, r2);
    }
}
