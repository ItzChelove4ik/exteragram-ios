package org.telegram.ui.community;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class CommunityUtils$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ CommunityUtils$$ExternalSyntheticLambda2(BaseFragment r1, TLRPC.Chat r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        CommunityUtils.m21562$r8$lambda$mperuEnk74TvbVNSIns2W07vRo(this.f$0, this.f$1, this.f$2, (TLRPC.Chat) r3);
    }
}
