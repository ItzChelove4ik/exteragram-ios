package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.GroupStickersActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupStickersActivity$SearchAdapter$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ GroupStickersActivity.SearchAdapter f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ GroupStickersActivity$SearchAdapter$$ExternalSyntheticLambda1(GroupStickersActivity.SearchAdapter r1, String r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        GroupStickersActivity.SearchAdapter.$r8$lambda$28pG1GL3rPyOIn6BZVHmDxdbeJ4(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
