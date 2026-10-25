package org.telegram.tgnet;

import java.util.concurrent.Executor;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ConnectionsManager$$ExternalSyntheticLambda19 implements Utilities.Callback2 {
    public final /* synthetic */ ConnectionsManager f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ ConnectionsManager$$ExternalSyntheticLambda19(ConnectionsManager r1, Executor r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        ConnectionsManager.m5390$r8$lambda$lTlJusPE9EDJXS3zXZ345UtmOE(this.f$0, this.f$1, this.f$2, (TLRPC.Updates) r3, (TLRPC.TL_error) r4);
    }
}
