package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class GroupCallActivity$$ExternalSyntheticLambda81 implements RequestDelegate {
    public final /* synthetic */ GroupCallActivity f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ AlertDialog[] f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ TL_phone.inviteToGroupCall f$5;

    public /* synthetic */ GroupCallActivity$$ExternalSyntheticLambda81(GroupCallActivity r1, long r2, AlertDialog[] r4, TLRPC.User r5, boolean r6, TL_phone.inviteToGroupCall r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        GroupCallActivity.$r8$lambda$nAm3p07aR3PgntgHSq1jQ2YZeQw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r10, r11);
    }
}
