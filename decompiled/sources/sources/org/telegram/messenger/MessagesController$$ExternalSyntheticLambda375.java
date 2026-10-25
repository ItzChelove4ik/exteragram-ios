package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda375 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ Long f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda375(MessagesController r1, Long r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MessagesController.$r8$lambda$3RTkDysP_0NpS8_yKa0JdnVy1X4(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
