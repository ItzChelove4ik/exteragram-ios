package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda76 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ TLRPC.TL_messages_editMessage f$3;
    public final /* synthetic */ BaseFragment f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda76(SendMessagesHelper r1, boolean r2, MessageObject r3, TLRPC.TL_messages_editMessage r4, BaseFragment r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        SendMessagesHelper.$r8$lambda$kL6bsNDnAfLuzpwWb8Tyhkn6PJY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
