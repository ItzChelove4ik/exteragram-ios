package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda41 implements RequestDelegate {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ TLRPC.InputPeer f$2;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda41(GroupCallActivity r1, TLRPC.Chat r2, TLRPC.InputPeer r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        GroupCallActivity.$r8$lambda$s77cPWN3wnyVGjqbcBqdwyzoXYU(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
