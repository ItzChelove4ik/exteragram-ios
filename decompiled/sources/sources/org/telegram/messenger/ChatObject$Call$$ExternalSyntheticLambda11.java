package org.telegram.messenger;

import org.telegram.messenger.ChatObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatObject$Call$$ExternalSyntheticLambda11 implements RequestDelegate {
    public final /* synthetic */ ChatObject.Call f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TL_phone.getGroupParticipants f$2;

    public /* synthetic */ ChatObject$Call$$ExternalSyntheticLambda11(ChatObject.Call r1, boolean r2, TL_phone.getGroupParticipants r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ChatObject.Call.$r8$lambda$9KXh5NXH0YnsFU9W8pXc5a2hwRY(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
