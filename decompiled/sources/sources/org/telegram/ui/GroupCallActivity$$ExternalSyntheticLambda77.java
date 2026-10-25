package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda77 implements Runnable {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TL_phone.exportGroupCallInvite f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda77(GroupCallActivity r1, AlertDialog r2, TLObject r3, TL_phone.exportGroupCallInvite r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GroupCallActivity.m15069$r8$lambda$xfyPCY85X5D5xNT2PtIMqB8ihk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
