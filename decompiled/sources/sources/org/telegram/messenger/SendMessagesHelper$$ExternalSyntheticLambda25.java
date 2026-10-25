package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda25 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda25(SendMessagesHelper r1, TLObject r2, int r3, SendMessagesHelper.DelayedMessage r4, ArrayList r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$K5a4fbp7aH6UorE1C62I_gMv3qA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
