package org.telegram.tgnet;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ConnectionsManager$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ Utilities.Callback2 f$0;
    public final /* synthetic */ TLRPC.Updates f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;

    public /* synthetic */ ConnectionsManager$$ExternalSyntheticLambda9(Utilities.Callback2 r1, TLRPC.Updates r2, TLRPC.TL_error r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConnectionsManager.$r8$lambda$YC3WzcJH0aIAt1sjqSzzEIVVXfE(this.f$0, this.f$1, this.f$2);
    }
}
