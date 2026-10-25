package org.telegram.messenger;

import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.ChatObject;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatObject$Call$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ ChatObject.Call f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ ChatObject.Call.OnParticipantsLoad f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ HashSet f$5;

    public /* synthetic */ ChatObject$Call$$ExternalSyntheticLambda10(ChatObject.Call r1, int r2, TLObject r3, ChatObject.Call.OnParticipantsLoad r4, ArrayList r5, HashSet r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatObject.Call.$r8$lambda$QI4Y7T5xApN2fU1VynraduWXWtM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
