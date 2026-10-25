package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda45 implements Runnable {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$1;
    public final /* synthetic */ TLRPC.Document f$2;
    public final /* synthetic */ ChatActivity f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda45(BaseFragment r1, TLRPC.TL_messages_stickerSet r2, TLRPC.Document r3, ChatActivity r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StickersAlert.$r8$lambda$CTRZBjce4AiDNtFGtVtRa8ehzWU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
