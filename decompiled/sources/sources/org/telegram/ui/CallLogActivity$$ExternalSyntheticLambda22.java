package org.telegram.ui;

import java.util.HashSet;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class CallLogActivity$$ExternalSyntheticLambda22 implements RequestDelegate {
    public final /* synthetic */ CallLogActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ HashSet f$2;
    public final /* synthetic */ TLRPC.TL_inputGroupCallInviteMessage f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ CallLogActivity$$ExternalSyntheticLambda22(CallLogActivity r1, AlertDialog r2, HashSet r3, TLRPC.TL_inputGroupCallInviteMessage r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        CallLogActivity.$r8$lambda$Bo6gEJhINE42WZ6lKNJrFi9zxuU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
