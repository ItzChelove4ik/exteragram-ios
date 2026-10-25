package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda552 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.TL_messageMediaWebPage f$1;
    public final /* synthetic */ TLRPC.TL_webPageAttributeStory f$2;
    public final /* synthetic */ Utilities.Callback2 f$3;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda552(ChatActivity r1, TLRPC.TL_messageMediaWebPage r2, TLRPC.TL_webPageAttributeStory r3, Utilities.Callback2 r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.m7468$r8$lambda$QyHpQUunnbZC76zGMB4LVviILY(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
