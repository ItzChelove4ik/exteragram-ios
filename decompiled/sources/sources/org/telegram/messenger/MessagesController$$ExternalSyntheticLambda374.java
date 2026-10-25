package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda374 implements Utilities.Callback2 {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda374(MessagesController r1, long r2, Utilities.Callback2 r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        MessagesController.$r8$lambda$exN3mVRxME7AjZp56mgHyiIIfAs(this.f$0, this.f$1, this.f$2, (TLRPC.Bool) r7, (TLRPC.TL_error) r8);
    }
}
