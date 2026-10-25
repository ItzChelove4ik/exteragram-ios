package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda113 implements Runnable {
    public final /* synthetic */ DialogsActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ TLRPC.Chat f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ TLRPC.TL_error f$7;
    public final /* synthetic */ TLRPC.TL_messages_checkHistoryImportPeer f$8;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda113(DialogsActivity r1, AlertDialog r2, TLObject r3, TLRPC.User r4, TLRPC.Chat r5, long r6, boolean r8, TLRPC.TL_error r9, TLRPC.TL_messages_checkHistoryImportPeer r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DialogsActivity.$r8$lambda$cWCfw8M7RDV4uLPrjo70geNT3lk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
