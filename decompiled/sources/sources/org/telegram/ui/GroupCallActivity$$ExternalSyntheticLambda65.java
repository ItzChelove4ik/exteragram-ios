package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda65 implements RequestDelegate {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TL_phone.exportGroupCallInvite f$2;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda65(GroupCallActivity r1, AlertDialog r2, TL_phone.exportGroupCallInvite r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        GroupCallActivity.m15064$r8$lambda$pmjWdj5FMoyX78FLzyz6qNYs(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
