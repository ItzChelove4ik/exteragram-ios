package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda67 implements Runnable {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ TLRPC.InputPeer f$2;
    public final /* synthetic */ TL_update.TL_updateGroupCall f$3;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda67(GroupCallActivity r1, TLRPC.Chat r2, TLRPC.InputPeer r3, TL_update.TL_updateGroupCall r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GroupCallActivity.$r8$lambda$ojO9j7ARvGBkSIoskanFEsHm90A(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
