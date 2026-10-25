package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChannelAdminLogActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelAdminLogActivity$ChatActivityAdapter$3$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ChannelAdminLogActivity.ChatActivityAdapter.AnonymousClass3 f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$1;
    public final /* synthetic */ TLRPC.TL_messages_exportedChatInvite f$2;
    public final /* synthetic */ boolean[] f$3;
    public final /* synthetic */ AlertDialog f$4;

    public /* synthetic */ ChannelAdminLogActivity$ChatActivityAdapter$3$$ExternalSyntheticLambda2(ChannelAdminLogActivity.ChatActivityAdapter.AnonymousClass3 r1, TLRPC.TL_chatInviteExported r2, TLRPC.TL_messages_exportedChatInvite r3, boolean[] r4, AlertDialog r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChannelAdminLogActivity.ChatActivityAdapter.AnonymousClass3.$r8$lambda$KFUidg5wJMEORoeKSNIEoqr9UEE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
