package org.telegram.messenger;

import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TL_account.RequirementToContact f$2;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda12(MessagesStorage r1, long r2, TL_account.RequirementToContact r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$37uT_LbhlZAadDUKqKOfb4pvrU0(this.f$0, this.f$1, this.f$2);
    }
}
