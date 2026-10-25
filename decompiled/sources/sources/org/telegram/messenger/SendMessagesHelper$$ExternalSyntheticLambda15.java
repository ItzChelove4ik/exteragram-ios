package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda15 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.TL_messages_forwardMessages f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda15(SendMessagesHelper r1, TLRPC.TL_messages_forwardMessages r2, ArrayList r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$ISBM8M1eM3VRBGoGUuvWk8yCiAk(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
