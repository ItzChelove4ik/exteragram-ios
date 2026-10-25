package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.GroupCallActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$19$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ GroupCallActivity.AnonymousClass19 f$0;

    public /* synthetic */ GroupCallActivity$19$$ExternalSyntheticLambda2(GroupCallActivity.AnonymousClass19 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        GroupCallActivity.AnonymousClass19.$r8$lambda$WBvGn6F4pzOphu_G3yMuIFwkJL0(this.f$0, r1, r2);
    }
}
