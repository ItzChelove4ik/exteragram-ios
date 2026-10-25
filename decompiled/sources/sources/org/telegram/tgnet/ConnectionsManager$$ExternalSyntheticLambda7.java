package org.telegram.tgnet;

import java.util.concurrent.Executor;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class ConnectionsManager$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ ConnectionsManager$$ExternalSyntheticLambda7(Executor r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ConnectionsManager.m5383$r8$lambda$8rHrGzkyrliJMokXSq2auMo358(this.f$0, this.f$1, r2, r3);
    }
}
