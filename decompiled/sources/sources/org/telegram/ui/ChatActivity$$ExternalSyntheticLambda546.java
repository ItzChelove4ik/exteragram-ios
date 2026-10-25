package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda546 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ MessageObject f$4;
    public final /* synthetic */ TLRPC.WebPage f$5;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda546(ChatActivity r1, AlertDialog r2, boolean[] r3, boolean r4, MessageObject r5, TLRPC.WebPage r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.$r8$lambda$Y04OjnV0fJ2cEYTXFkKxlHKKNOk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
