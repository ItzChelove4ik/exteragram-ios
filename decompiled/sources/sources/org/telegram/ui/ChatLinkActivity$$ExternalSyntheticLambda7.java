package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatLinkActivity$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ ChatLinkActivity f$0;
    public final /* synthetic */ AlertDialog[] f$1;
    public final /* synthetic */ TLRPC.Chat f$2;
    public final /* synthetic */ BaseFragment f$3;

    public /* synthetic */ ChatLinkActivity$$ExternalSyntheticLambda7(ChatLinkActivity r1, AlertDialog[] r2, TLRPC.Chat r3, BaseFragment r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ChatLinkActivity.m8346$r8$lambda$jns7aeeujpTWk7tOtjN4xvyo30(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
