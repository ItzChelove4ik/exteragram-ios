package org.telegram.tgnet;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class ConnectionsManager$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ Utilities.Callback2 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;

    public /* synthetic */ ConnectionsManager$$ExternalSyntheticLambda23(Utilities.Callback2 r1, TLObject r2, TLRPC.TL_error r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConnectionsManager.m5385$r8$lambda$M6M3sMK7a08kPvg9UWzD48uYs(this.f$0, this.f$1, this.f$2);
    }
}
