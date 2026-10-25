package org.telegram.messenger;

import org.telegram.messenger.ChatObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_phone;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatObject$Call$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ ChatObject.Call f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TL_phone.getGroupParticipants f$3;

    public /* synthetic */ ChatObject$Call$$ExternalSyntheticLambda6(ChatObject.Call r1, boolean r2, TLObject r3, TL_phone.getGroupParticipants r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatObject.Call.$r8$lambda$eoOUvrZMLleqlBXm_GGIH0srX58(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
