package org.telegram.messenger;

import androidx.core.util.Consumer;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda219 implements Utilities.Callback2 {
    public final /* synthetic */ Consumer f$0;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda219(Consumer r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        MessagesController.m4490$r8$lambda$2b4BKB5veZSwxIwVOWodBEFhdo(this.f$0, (TLRPC.messages_Messages) r1, (TLRPC.TL_error) r2);
    }
}
