package org.telegram.ui.Stars;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda60 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ long f$6;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda60(StarsController r1, Runnable r2, boolean r3, long r4, int r6, MessageObject r7, long r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        StarsController.$r8$lambda$awga3ygleA5XaS5AGIopEjK9oDU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r12, r13);
    }
}
