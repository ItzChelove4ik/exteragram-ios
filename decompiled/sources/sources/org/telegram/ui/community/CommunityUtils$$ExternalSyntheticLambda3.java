package org.telegram.ui.community;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class CommunityUtils$$ExternalSyntheticLambda3 implements Utilities.Callback {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.Chat f$2;
    public final /* synthetic */ TLRPC.Chat f$3;

    public /* synthetic */ CommunityUtils$$ExternalSyntheticLambda3(BaseFragment r1, int r2, TLRPC.Chat r3, TLRPC.Chat r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        CommunityUtils.$r8$lambda$qoYnhsjMxmmpmirw26LOZCaGsak(this.f$0, this.f$1, this.f$2, this.f$3, (Boolean) r4);
    }
}
