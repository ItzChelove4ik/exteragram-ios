package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$SavedMusicList$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ MessagesController.SavedMusicList f$0;

    public /* synthetic */ MessagesController$SavedMusicList$$ExternalSyntheticLambda0(MessagesController.SavedMusicList r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        MessagesController.SavedMusicList.m4665$r8$lambda$nWSGnKYQNlmzk3fNEJkDthmq94(this.f$0, r1, r2);
    }
}
