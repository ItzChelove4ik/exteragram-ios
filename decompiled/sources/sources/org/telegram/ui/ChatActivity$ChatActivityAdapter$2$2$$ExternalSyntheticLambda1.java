package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatActivityAdapter$2$2$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ ChatActivity.ChatActivityAdapter.AnonymousClass2.C00452 f$0;
    public final /* synthetic */ MessageObject f$1;

    public /* synthetic */ ChatActivity$ChatActivityAdapter$2$2$$ExternalSyntheticLambda1(ChatActivity.ChatActivityAdapter.AnonymousClass2.C00452 r1, MessageObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ChatActivity.ChatActivityAdapter.AnonymousClass2.C00452.$r8$lambda$7eEnaLS7cGA36ho2FX1BhQDPDo8(this.f$0, this.f$1, r2, r3);
    }
}
