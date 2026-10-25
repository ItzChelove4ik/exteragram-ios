package org.telegram.ui.Stars;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda38 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda38(StarsController r1, long r2, long r4, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        StarsController.$r8$lambda$6w1SftkhUIw6M92RElIffKZxkV8(this.f$0, this.f$1, this.f$2, this.f$3, r9, r10);
    }
}
