package org.telegram.ui;

import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class BoostsActivity$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ BoostsActivity f$0;
    public final /* synthetic */ CountDownLatch f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ BoostsActivity$$ExternalSyntheticLambda10(BoostsActivity r1, CountDownLatch r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        BoostsActivity.m6284$r8$lambda$Z0gOsNipnnP902fxBSrnXNOpEA(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
