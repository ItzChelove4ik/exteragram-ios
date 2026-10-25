package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda136 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.TL_game f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda136(ChatActivity r1, TLRPC.TL_game r2, MessageObject r3, String r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r9, int r10) {
        ChatActivity.$r8$lambda$NV1_Cb22nfXlpTT0o9UXNOcnlRc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
