package org.telegram.ui;

import java.util.HashSet;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda36 implements Runnable {
    public final /* synthetic */ CallLogActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ HashSet f$3;
    public final /* synthetic */ TLRPC.TL_inputGroupCallInviteMessage f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ TLRPC.TL_error f$6;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda36(CallLogActivity r1, AlertDialog r2, TLObject r3, HashSet r4, TLRPC.TL_inputGroupCallInviteMessage r5, boolean r6, TLRPC.TL_error r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CallLogActivity.m6533$r8$lambda$uJgjzr2fTQ0t49ePNCgTu8vOc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
