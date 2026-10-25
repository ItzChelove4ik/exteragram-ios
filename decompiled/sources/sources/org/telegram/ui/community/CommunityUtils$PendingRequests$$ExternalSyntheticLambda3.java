package org.telegram.ui.community;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_communities;
import org.telegram.ui.community.CommunityUtils;

/* loaded from: classes7.dex */
public final /* synthetic */ class CommunityUtils$PendingRequests$$ExternalSyntheticLambda3 implements Utilities.Callback2 {
    public final /* synthetic */ CommunityUtils.PendingRequests f$0;

    public /* synthetic */ CommunityUtils$PendingRequests$$ExternalSyntheticLambda3(CommunityUtils.PendingRequests r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        CommunityUtils.PendingRequests.m21564$r8$lambda$WBUE8EcTSG7VIQvHByJC8u7QKI(this.f$0, (TL_communities.PeerLinkRequests) r1, (TLRPC.TL_error) r2);
    }
}
