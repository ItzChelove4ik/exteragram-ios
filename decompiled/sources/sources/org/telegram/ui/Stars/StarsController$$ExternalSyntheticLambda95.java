package org.telegram.ui.Stars;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda95 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda95(StarsController r1, MessageObject r2, long r3, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        StarsController.$r8$lambda$WCmMNKVKUxmHwLLEnVnHmxKk_Rk(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
