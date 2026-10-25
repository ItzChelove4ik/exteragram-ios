package org.telegram.messenger;

import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.ChatObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatObject$Call$$ExternalSyntheticLambda12 implements RequestDelegate {
    public final /* synthetic */ ChatObject.Call f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ChatObject.Call.OnParticipantsLoad f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ HashSet f$4;

    public /* synthetic */ ChatObject$Call$$ExternalSyntheticLambda12(ChatObject.Call r1, int r2, ChatObject.Call.OnParticipantsLoad r3, ArrayList r4, HashSet r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        ChatObject.Call.m3920$r8$lambda$qheNEaMS9zWZFvjziJ7cnG5YeQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
