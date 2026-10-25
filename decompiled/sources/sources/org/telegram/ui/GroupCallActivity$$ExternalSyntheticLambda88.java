package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda88 implements Runnable {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ AlertDialog[] f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ TL_phone.inviteToGroupCall f$5;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda88(GroupCallActivity r1, AlertDialog[] r2, boolean r3, TLRPC.TL_error r4, long r5, TL_phone.inviteToGroupCall r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GroupCallActivity.$r8$lambda$OCAaDy50JxS_yignaPS1lLlzcc4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
