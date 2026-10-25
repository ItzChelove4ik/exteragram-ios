package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda210 implements Utilities.Callback {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.TL_document f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Object f$3;
    public final /* synthetic */ MessageObject.SendAnimationData f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda210(ChatActivity r1, TLRPC.TL_document r2, String r3, Object r4, MessageObject.SendAnimationData r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        ChatActivity.$r8$lambda$iDU__P7DTqcELZHTm6CRQjC5fAI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (Long) r7);
    }
}
