package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda279 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ Long f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda279(MessagesController r1, TLRPC.TL_error r2, TLObject r3, Long r4, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$0CIvGGKZtsXAu_AWDnoj18iq_y8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
