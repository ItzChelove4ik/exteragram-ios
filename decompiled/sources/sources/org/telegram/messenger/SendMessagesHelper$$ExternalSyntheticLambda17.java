package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda17 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.Message f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ ArrayList f$5;
    public final /* synthetic */ int f$6;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda17(SendMessagesHelper r1, boolean r2, TLRPC.Message r3, ArrayList r4, boolean r5, ArrayList r6, int r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4937$r8$lambda$oVQfSnsHYyPC1jbIDg3Md8xveo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
