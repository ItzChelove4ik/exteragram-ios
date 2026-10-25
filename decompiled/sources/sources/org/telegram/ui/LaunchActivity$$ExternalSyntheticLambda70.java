package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda70 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda70(LaunchActivity r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LaunchActivity.m15647$r8$lambda$zX_imISOm2o_YJkN8e0gKQKybQ(this.f$0, this.f$1, r2, r3);
    }
}
