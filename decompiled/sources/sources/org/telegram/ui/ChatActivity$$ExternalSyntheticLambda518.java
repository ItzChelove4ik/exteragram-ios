package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda518 implements Utilities.Callback {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda518(ChatActivity r1, TLRPC.User r2, TLRPC.TL_attachMenuBot r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        ChatActivity.m7457$r8$lambda$Mn3gf9Zlka5pAoGlPutF6J75Vk(this.f$0, this.f$1, this.f$2, (Boolean) r3);
    }
}
