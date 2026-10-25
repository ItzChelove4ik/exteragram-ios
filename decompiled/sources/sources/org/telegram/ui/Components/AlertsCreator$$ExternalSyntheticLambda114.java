package org.telegram.ui.Components;

import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.ChatActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda114 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ TLRPC.User f$0;
    public final /* synthetic */ AccountInstance f$1;
    public final /* synthetic */ ChatActivity f$2;
    public final /* synthetic */ TLRPC.Chat f$3;
    public final /* synthetic */ MessageObject f$4;
    public final /* synthetic */ CheckBoxCell[] f$5;
    public final /* synthetic */ Theme.ResourcesProvider f$6;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda114(TLRPC.User r1, AccountInstance r2, ChatActivity r3, TLRPC.Chat r4, MessageObject r5, CheckBoxCell[] r6, Theme.ResourcesProvider r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r10, int r11) {
        AlertsCreator.m8767$r8$lambda$nZty1amtSGod2dJ4IIrC60e7KI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10, r11);
    }
}
