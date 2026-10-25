package org.telegram.ui.Components;

import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Cells.CheckBoxCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda81 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ TLRPC.User f$0;
    public final /* synthetic */ AccountInstance f$1;
    public final /* synthetic */ CheckBoxCell[] f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ TLRPC.Chat f$4;
    public final /* synthetic */ TLRPC.EncryptedChat f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ MessagesStorage.IntCallback f$7;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda81(TLRPC.User r1, AccountInstance r2, CheckBoxCell[] r3, long r4, TLRPC.Chat r6, TLRPC.EncryptedChat r7, boolean r8, MessagesStorage.IntCallback r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r12, int r13) {
        AlertsCreator.$r8$lambda$vnc7xQJSMx1gukA4o_qv4Liibm4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r12, r13);
    }
}
