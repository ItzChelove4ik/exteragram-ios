package org.telegram.messenger.voip;

import org.telegram.messenger.AccountInstance;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ AccountInstance f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda8(VoIPService r1, TLObject r2, AccountInstance r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.$r8$lambda$FzV2DSw3y93_6YhO3oMErqiB7vM(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
