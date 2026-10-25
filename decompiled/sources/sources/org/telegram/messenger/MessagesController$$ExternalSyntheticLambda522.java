package org.telegram.messenger;

import androidx.core.util.Consumer;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda522 implements MessagesController.ErrorDelegate {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda522(Consumer r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesController.ErrorDelegate
    public final boolean run(TLRPC.TL_error r2) {
        return MessagesController.m4646$r8$lambda$wOSpydftRrHmukgYhJot2xfjaM(this.f$0, this.f$1, r2);
    }
}
