package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda434 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ TLRPC.updates_Difference f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda434(MessagesController r1, ArrayList r2, TLRPC.updates_Difference r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$EAgF_027Cgn4YU8kDtmsO4ezJyk(this.f$0, this.f$1, this.f$2);
    }
}
