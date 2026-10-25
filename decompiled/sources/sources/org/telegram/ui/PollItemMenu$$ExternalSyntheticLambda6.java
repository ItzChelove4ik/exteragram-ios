package org.telegram.ui;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class PollItemMenu$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ PollItemMenu f$0;
    public final /* synthetic */ ChatActivity f$1;
    public final /* synthetic */ TLRPC.PollAnswer f$2;

    public /* synthetic */ PollItemMenu$$ExternalSyntheticLambda6(PollItemMenu r1, ChatActivity r2, TLRPC.PollAnswer r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PollItemMenu.$r8$lambda$ue2QUcOGrB29anmfFdY_Pj_icvU(this.f$0, this.f$1, this.f$2);
    }
}
