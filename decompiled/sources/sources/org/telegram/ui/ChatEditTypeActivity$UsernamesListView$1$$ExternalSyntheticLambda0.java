package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChatEditTypeActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda0 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatEditTypeActivity.UsernamesListView.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_username f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda0(ChatEditTypeActivity.UsernamesListView.AnonymousClass1 r1, TLRPC.TL_username r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        ChatEditTypeActivity.UsernamesListView.AnonymousClass1.m8339$r8$lambda$9VT8gldfBMTa1Cq6_635wxL5qY(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
