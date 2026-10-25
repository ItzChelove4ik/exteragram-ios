package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_communities;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda275 implements Utilities.Callback2 {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda275(MessagesController r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        MessagesController.$r8$lambda$hhm50V3FD2QgIuE9JwHjVTfX1OM(this.f$0, this.f$1, (TL_communities.PeerLinkRequests) r2, (TLRPC.TL_error) r3);
    }
}
