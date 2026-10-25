package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda403 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda403(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        MessagesController.m4481$r8$lambda$0lZIg2RU6g3WnTNYFXZTrTLsFI(this.f$0, r1, r2);
    }
}
