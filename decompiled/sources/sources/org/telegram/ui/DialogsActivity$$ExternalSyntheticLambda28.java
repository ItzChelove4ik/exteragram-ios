package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda28 implements RequestDelegate {
    public final /* synthetic */ DialogsActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ TLRPC.Chat f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ TLRPC.TL_messages_checkHistoryImportPeer f$6;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda28(DialogsActivity r1, AlertDialog r2, TLRPC.User r3, TLRPC.Chat r4, long r5, boolean r7, TLRPC.TL_messages_checkHistoryImportPeer r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        DialogsActivity.m14359$r8$lambda$BrUw4paupBtGuBZckA18Q8HIs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
