package org.telegram.ui;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChatEditTypeActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda1 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatEditTypeActivity.UsernamesListView.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_username f$1;
    public final /* synthetic */ View f$2;

    public /* synthetic */ ChatEditTypeActivity$UsernamesListView$1$$ExternalSyntheticLambda1(ChatEditTypeActivity.UsernamesListView.AnonymousClass1 r1, TLRPC.TL_username r2, View r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        ChatEditTypeActivity.UsernamesListView.AnonymousClass1.m8338$r8$lambda$62TsLsO0pCW1PFFawrVtCtlCt4(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
