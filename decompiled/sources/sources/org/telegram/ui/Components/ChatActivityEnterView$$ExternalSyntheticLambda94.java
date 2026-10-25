package org.telegram.ui.Components;

import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.SenderSelectPopup;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda94 implements SenderSelectPopup.OnSelectCallback {
    public final /* synthetic */ ChatActivityEnterView f$0;
    public final /* synthetic */ TLRPC.ChatFull f$1;
    public final /* synthetic */ MessagesController f$2;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda94(ChatActivityEnterView r1, TLRPC.ChatFull r2, MessagesController r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.SenderSelectPopup.OnSelectCallback
    public final void onPeerSelected(RecyclerView r7, SenderSelectPopup.SenderView r8, TLRPC.Peer r9) {
        ChatActivityEnterView.$r8$lambda$LBq5vIORGjLvPY69Y9OyChIi3do(this.f$0, this.f$1, this.f$2, r7, r8, r9);
    }
}
