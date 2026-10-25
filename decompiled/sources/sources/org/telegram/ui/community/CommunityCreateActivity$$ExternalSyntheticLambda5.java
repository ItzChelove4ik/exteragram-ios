package org.telegram.ui.community;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class CommunityCreateActivity$$ExternalSyntheticLambda5 implements Utilities.Callback {
    public final /* synthetic */ CommunityCreateActivity f$0;
    public final /* synthetic */ TLRPC.Chat f$1;

    public /* synthetic */ CommunityCreateActivity$$ExternalSyntheticLambda5(CommunityCreateActivity r1, TLRPC.Chat r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        CommunityCreateActivity.$r8$lambda$iAHWFUcfWLoAgkPy8W9TjpdlgsE(this.f$0, this.f$1, (Boolean) r2);
    }
}
