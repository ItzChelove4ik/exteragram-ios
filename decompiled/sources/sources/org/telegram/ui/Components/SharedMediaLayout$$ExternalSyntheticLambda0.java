package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ SharedMediaLayout f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ SharedMediaLayout$$ExternalSyntheticLambda0(SharedMediaLayout r1, int r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        SharedMediaLayout.$r8$lambda$_keaWgTVsD_fy5xexDkEduLM6LY(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
