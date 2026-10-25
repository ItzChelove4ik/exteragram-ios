package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda71 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda71(GroupCallActivity r1, TLRPC.User r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r7, int r8) {
        GroupCallActivity.m15060$r8$lambda$jzX359AsX_Mgcj_JI2agdV11Rw(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
