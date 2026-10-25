package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda71 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda71(LaunchActivity r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LaunchActivity.$r8$lambda$SIgMC1aOBLNYudpddKhr4E_f1gY(this.f$0, this.f$1, r2, r3);
    }
}
