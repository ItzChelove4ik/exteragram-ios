package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class UserNameResolver$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ UserNameResolver f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLObject f$3;

    public /* synthetic */ UserNameResolver$$ExternalSyntheticLambda2(UserNameResolver r1, String r2, TLRPC.TL_error r3, TLObject r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UserNameResolver.$r8$lambda$AAxbrfaFM_Odb78sc700dSlzdtg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
